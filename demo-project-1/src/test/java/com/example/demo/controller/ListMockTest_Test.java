//package com.example.demo.controller;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.demo.data.MockTestRepository;
//import com.example.demo.data.SubjectRepository;
//import com.example.demo.data.UserRepository;
//import com.example.demo.entity.MockTest;
//import com.example.demo.entity.Subject;
//import com.example.demo.entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.Model;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//@ExtendWith(MockitoExtension.class)
//public class ListMockTest_Test {
//
//    @InjectMocks
//    private ExampleController controller;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private SubjectRepository subjectRepository;
//
//    @Mock
//    private MockTestRepository mockTestRepository;
//
//    @Mock
//    private Model model;
//
//    @Mock
//    private Authentication auth;
////
//    @Mock
//    private SecurityContext securityContext;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.setContext(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        when(auth.getName()).thenReturn("user1");
//
//        User user = new User();
//        user.setId(1);
//        user.setName("user1");
//        when(userRepository.findUserByName("user1")).thenReturn(user);
//    }
//
//    private MockTest createMockTest(Integer id, String title) {
//        MockTest mockTest = new MockTest();
//        mockTest.setId(id);
//        mockTest.setTitle(title);
//        mockTest.setSubjectId(1);
//        mockTest.setStart(LocalDateTime.now());
//        mockTest.setEnd(LocalDateTime.now().plusHours(1));
//        return mockTest;
//    }
//
//    @Test
//    public void listMockTestWithoutKeyword() {
//        Subject subject = new Subject();
//        MockTest test1 = createMockTest(1, "Test1");
//        MockTest test2 = createMockTest(2, "Test2");
//        List<MockTest> mock = new ArrayList<>();
//        mock.add(test1);
//        mock.add(test2);
//
//        when(subjectRepository.findSubjectByUserID(1)).thenReturn(subject);
//        when(mockTestRepository.findMockTestByUserId(1)).thenReturn(mock);
//        String actual = controller.listMockTest(model, null);
//        assertTrue(mock.containsAll(mockTestRepository.findMockTestByUserId(1)));
//
//        assertEquals("home", actual);
//        verify(model).addAttribute("subject", subject);
//        verify(model).addAttribute("mock", mock);
//    }
//
//    @Test
//    public void listMockTestWithKeyword() {
//        Subject subject = new Subject();
//        MockTest test1 = createMockTest(1, "Test1");
//        MockTest test2 = createMockTest(2, "Test2");
//        List<MockTest> mock = new ArrayList<>();
//        mock.add(test1);
//        mock.add(test2);
//        when(subjectRepository.findSubjectByUserID(1)).thenReturn(subject);
//        when(mockTestRepository.searchMockTest(1,"Test1")).thenReturn(mock);
//        String actual = controller.listMockTest(model, "Test1");
//        assertTrue(mock.containsAll(mockTestRepository.findMockTestByUserId(1)));
//
//        assertEquals("home", actual);
//        verify(model).addAttribute("subject", subject);
//        verify(model).addAttribute("mock", mock);
//    }
//}
