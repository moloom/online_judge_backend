package com.mo.oj.service.impl;

import com.mo.oj.mapper.ProblemsMapper;
import com.mo.oj.pojo.Tag;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemsServiceImpl implements ProblemsService {

    @Autowired
    ProblemsMapper problemsMapper;

    /**
     * 查询所有的标签
     *
     * @return
     */
    @Override
    public List<Tag> searchTagAll() {
        return this.problemsMapper.searchTagAll();
    }
}
