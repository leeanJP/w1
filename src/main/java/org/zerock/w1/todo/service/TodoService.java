package org.zerock.w1.todo.service;

import org.zerock.w1.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {
    //서비스 객체는 기능들의 묶음
    //CRUD 기능들을 모두 서비스 객체에 모아서 구현된다.

    //enum 타입으로 클래스를 작성하는 경우
    //정해진 수만큼만 객체를 생성할 수 있다.
    INSTANCE;
    //TodoService.INSTANCE 항상 하나의 객체를 가르킨다.
    //객체를 하나만 생성해서 사용한다.
    //싱글톤 패턴이라고 한다.

    public void register(TodoDTO todoDTO){
        System.out.println("DEBUG ... " + todoDTO);

    }

    public List<TodoDTO> getList() {

        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i->{
            TodoDTO dto = new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("TODO..." + i);
            dto.setDueDate(LocalDate.now());
            return dto;
        }).collect(Collectors.toList());

        return todoDTOS;
    }

    public TodoDTO get(Long tno){
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample TODO");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);
        return dto;
    }

}
