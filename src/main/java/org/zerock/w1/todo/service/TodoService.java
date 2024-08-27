package org.zerock.w1.todo.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w1.todo.dao.TodoDAO;
import org.zerock.w1.todo.domain.TodoVO;
import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.util.MapperUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {
    //서비스 객체는 기능들의 묶음
    //CRUD 기능들을 모두 서비스 객체에 모아서 구현된다.

    //enum 타입으로 클래스를 작성하는 경우
    //정해진 수만큼만 객체를 생성할 수 있다.
    INSTANCE;
    //TodoService.INSTANCE 항상 하나의 객체를 가르킨다.
    //객체를 하나만 생성해서 사용한다.
    //싱글톤 패턴이라고 한다.

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTACE.get();
    }


    public void register(TodoDTO todoDTO) throws Exception{

        TodoVO todoVO = modelMapper.map(todoDTO , TodoVO.class);
        //System.out.println(todoVO);
        log.info("***"+ todoVO);
        dao.insert(todoVO);


    }

    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = dao.selectAll();

        log.info("listAll .........");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
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

    public TodoDTO get(Long tno) throws Exception{

        log.info(" tno ::" + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO dto =  modelMapper.map(todoVO, TodoDTO.class);

        return dto;
    }

    public void remove(Long tno) throws Exception{

        log.info(" tno ::" + tno);
        dao.deleteOne(tno);

    }

    public void modify(TodoDTO todoDTO)throws Exception{
        log.info(" todoDTO ::" + todoDTO);

        TodoVO vo = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(vo);


    }


}
