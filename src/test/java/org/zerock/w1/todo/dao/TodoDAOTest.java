package org.zerock.w1.todo.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.domain.TodoVO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoDAOTest {

    private TodoDAO dao;

    //BeforeAll : 테스트 클래스를 초기화할 때 딱 한번 실행되는 메소드
    //BeforeEach : 테스트 메소드 실행 전에 반복 수행되는 메소드
    //AfterAll :테스트 메소드를 모두 실행시킨 후 딱 한번 실행되는 메소드
    //AfterEach : 테스트 메소드 실행 후에 반복 수행되는 메소드


    @BeforeEach
    public void ready(){
        System.out.println("ready 확인");
        dao = new TodoDAO();
    }


    @Test
    public void testInsert() throws Exception{
        TodoVO vo = TodoVO.builder()
                .title("sample title 1111")
                .dueDate(LocalDate.of(2024,8,28))
                .finished(true)
                .build();

        dao.insert(vo);
    }


    @Test
    void getTime() throws Exception{

        System.out.println("현재시간 : " + dao.getTime());

    }


}