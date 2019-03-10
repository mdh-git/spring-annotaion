package com.mdh.service;

import com.mdh.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class BookService {

    //@Qualifier("bookDao")
    //@Autowired(required = false)
    //@Resource
    @Inject
    private BookDao bookDao;

    public void print(){
        System.out.println("BookService -- >BookDao:" + bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
