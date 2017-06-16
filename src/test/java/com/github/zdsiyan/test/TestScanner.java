package com.github.zdsiyan.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zdsiyan.test.annotation.Mark;
import com.github.zdsiyan.test.entity.ContainDemo;
import com.github.zdsiyan.test.entity.Demo;
import com.github.zdsiyan.utils.AnnotationScanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestScanner extends TestCase {

  public TestScanner(String testName){
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestScanner.class);
  }
  
  Demo demo;
  List<Demo> list;
  Map<String, List> map;
  ContainDemo cdemo;
  
  protected void setUp() throws Exception {
    demo = new Demo();
    demo.setLg1(1L);
    demo.setLg2(2L);
    demo.setLg3(3L);
    
    demo.setStr1("STR1");
    demo.setStr2("STR2");
    demo.setStr3("STR3");
    
    cdemo = new ContainDemo();
    
    map = new HashMap<String,List>();
    list = new ArrayList<Demo>();
    list.add(demo);
    map.put("key", list);
    
    cdemo.setMap(map);
    cdemo.setDemo(demo);
    cdemo.setStr("STR");
  }
  
  public void testDemo(){
    
    AnnotationScanner<Mark> scanner = new AnnotationScanner<Mark>(){
      @Override
      protected void record(Field field, Object value, Mark mark) {
        System.out.println("testDemo\tfield:"+field.getName()+"\tmark:"+mark.value()+"\tvalue:"+value);
        
      }
    };
    
    scanner.field(demo, Mark.class);
  }
  
  public void testList(){
    AnnotationScanner<Mark> scanner = new AnnotationScanner<Mark>(){
      @Override
      protected void record(Field field, Object value, Mark mark) {
        System.out.println("testList\tfield:"+field.getName()+"\tmark:"+mark.value()+"\tvalue:"+value);
        
      }
    };
    scanner.field(list, Mark.class);
  }
  
  public void testMap(){
    AnnotationScanner<Mark> scanner = new AnnotationScanner<Mark>(){
      @Override
      protected void record(Field field, Object value, Mark mark) {
        System.out.println("testMap\t\tfield:"+field.getName()+"\tmark:"+mark.value()+"\tvalue:"+value);
        
      }
    };
    scanner.field(map, Mark.class);
  }
  
  public void testCustom(){
    AnnotationScanner<Mark> scanner = new AnnotationScanner<Mark>(){
      @Override
      protected void record(Field field, Object value, Mark mark) {
        System.out.println("testCustom\tfield:"+field.getName()+"\tmark:"+mark.value()+"\tvalue:"+value);
        
      }
    };
    
    scanner.field(cdemo, Mark.class);
  }
}
