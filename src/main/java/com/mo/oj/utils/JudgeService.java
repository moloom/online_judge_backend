package com.mo.oj.utils;

import com.mo.oj.pojo.Submission;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: mo
 * @date: 2022/4/14 下午9:01
 * @description: 判题服务
 */
public class JudgeService {
    private volatile String path = "/tem/temWorkSpace/";//工作目录
    private volatile String[] language = {"", "c", "java"};//编程语言源文件的后缀
    private volatile String useCaseDir = "/tem/useCaseDir/";//测试用例文件夹

    public Submission judge(Submission submission) {
        try {
//            System.out.println(Thread.currentThread().getName() + "----------" + this.toString());
            //生成一个20位的临时文件夹名称,
            String randomString = RandomString.getRandomString(20);
            //和工作目录拼接，成为此次提交的临时编译目录
            String dirName = path + randomString;
            File dirFile = new File(dirName);
            //如果路径不存在，则创建路径
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            //源代码文件名
            String codeFileName = "Main." + language[submission.getLanguage()];
            //源代码全路径
            String codeFullPath = dirName + "/" + codeFileName;
            //根据提交的语言类型，生成对应的源文件
            File codeFile = new File(codeFullPath);
            FileOutputStream fileOutputStream = new FileOutputStream(codeFile);
            //创建一个新的空文件。
            codeFile.createNewFile();
            //把代码写入到文件
            fileOutputStream.write(submission.getCode().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            //编译代码
            Process compileProcess;
            if (submission.getLanguage() == 1) {
                compileProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", "gcc " + codeFileName + " -o Main.out"}, null, dirFile);
            } else
                compileProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", "javac " + codeFileName}, null, dirFile);
            //编译错误，保存错误信息，中断运行
            if (compileProcess.waitFor() == 1) {
                //设置错误类型
                submission.setStatus(5);
                //读取错误信息
                BufferedInputStream in = new BufferedInputStream(compileProcess.getErrorStream());
                BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
                String line = null;
                submission.setError_info("");//先初始化下
                //把错误信息记录起来
                while ((line = br1.readLine()) != null) {
                    System.out.println(line);
                    submission.setError_info(submission.getError_info() + line + "\n");
                }
                System.out.println("编译错误");
                return submission;
            }
            //编译成功，则评测代码
            //拼接执行语句
            String command = "/tem/javaTest/judge/a.out -t 2000 -m 65536 -f 4096 --basedir " + dirName + " --datadir " + useCaseDir + submission.getProblem_id() + " --who 65534 --magic userOutput --end ";
            Process execProcess;
            if (submission.getLanguage() == 1) {
//                execProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", command+"./Main.out"}, null, dirFile);
                execProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", command + " ./Main.out"}, null, dirFile);
            } else {
                execProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", command + " java Main"}, null, dirFile);
            }


            //获取执行信息
            InputStream fis = execProcess.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            ArrayList<String> arrayList = new ArrayList<String>();
            int i = 0;
            //直到读完为止
            while ((line = br.readLine()) != null) {
                arrayList.add(line);
            }
            //执行完代码后。删除源代码文件
            codeFile.delete();
            //删除.class 或者 .out文件
            String compileFileName;
            if (submission.getLanguage() == 1)
                compileFileName = dirName + "/Main.out";
            else compileFileName = dirName + "/Main.class";
            new File(compileFileName).delete();
            //删除临时文件夹
            dirFile.delete();
            //记录执行状态
            submission.setStatus(Integer.parseInt(arrayList.get(0)));
            //判断执行状态，来记录执行信息
            if (arrayList.get(0).equalsIgnoreCase("1")) {
                //如果是正常通过，记录消耗内存和执行时间
                submission.setConsume_memory(Integer.parseInt(arrayList.get(2)));
                submission.setExec_time(Integer.parseInt(arrayList.get(1)));
                //如果执行时间为0，则赋值为1
                if (submission.getExec_time() == 0)
                    submission.setExec_time(1);
            } else {
                //如果不是正常通过，记录出错信息
                if (arrayList.size() > 1 && arrayList.get(1) != null)
                    submission.setError_info(arrayList.get(1));
                if (arrayList.size() > 2 && arrayList.get(2) != null)
                    submission.setError_info(submission.getError_info() + "\n" + arrayList.get(2));
            }
            return submission;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
