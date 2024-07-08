//package com.example.demo.controller;
//
//import com.example.demo.data.UserRepository;
//import com.example.demo.entity.ChangePass;
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
//import static org.mockito.Mockito.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class ChangePassWordTest {
//
//    @InjectMocks
//    private ExampleController controller;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private Model model;
//
//    @Mock
//    private Authentication auth;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.setContext(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        when(auth.getName()).thenReturn("user1");
//        User user1 = new User();
//        user1.setPassword("{noop}correct");
//        when(userRepository.findUserByName("user1")).thenReturn(user1);
//    }
//
//    @Test
//    public void ifOldPasswordIncorrect() {
//        ChangePass changePass = new ChangePass();
//        changePass.setOldpassword("wrong");
//        changePass.setNewpassword("123");
//        changePass.setReenter("123");
//
//        String actual = controller.changePassWord(changePass, model);
//        assertEquals("changepassword", actual);
//        verify(model).addAttribute("error", "Incorrect Password");
//    }
//
//    @Test
//    public void ifNewPasswordNotMatch() {
//        ChangePass changePass = new ChangePass();
//        changePass.setOldpassword("correct");
//        changePass.setNewpassword("123");
//        changePass.setReenter("456");
//
//        String actual = controller.changePassWord(changePass, model);
//        assertEquals("changepassword", actual);
//        verify(model).addAttribute("error", "Password does not match");
//    }
//    @Test
//    public void changePassworsSuccess() {
//        ChangePass changePass = new ChangePass();
//        changePass.setOldpassword("correct");
//        changePass.setNewpassword("123");
//        changePass.setReenter("123");
//        String actual = controller.changePassWord(changePass, model);
//        assertEquals("redirect:/test/homepage", actual);
//        verify(userRepository).save(any(User.class));
//    }
//}
//
//
//
//
