package hwan.board.board_project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hwan.board.board_project.config.UserDetailsImpl;
import hwan.board.board_project.domain.Member;
import hwan.board.board_project.dto.PostDTO;
import hwan.board.board_project.repository.MemberRepository;
import hwan.board.board_project.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setupUser() {
        memberRepository.deleteAll();
    }

    @Test
    void testPostBoardWrite() throws JsonProcessingException, Exception {
        Member test = Member.builder()
                .username("test99")
                .name("test9")
                .nickname("test5")
                .password("test6")
                .build();
        memberRepository.save(test);

        UserDetails testU = new UserDetailsImpl(test);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(testU, testU.getPassword(), testU.getAuthorities()));


        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("제목");
        postDTO.setContent("내용");

        mvc.perform(post("/freeboard/posting")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(postDTO))
                            );
    }

    @Test
    void testBoardList() {

    }

    @Test
    void testGetBoardWrite() {

    }
}
