package bstu.merh.employees;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(PageTest.class);
//    integr
    @Test
    public void loadMainPage() throws Exception {
        logger.info("running main page test");
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().is2xxSuccessful()); }

    @Test
    public void LoadUser() throws Exception {
        mockMvc.perform(post("/users")).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    //comp
    @Test
    public void testControllerAdvice() throws Exception
    {
        logger.info("running controller advice test");
        mockMvc.perform(get("/tests/exception"))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws ControllerException, UnsupportedEncodingException {
                        result.getResponse().getContentAsString().contains("test exception");
                    }
                })
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testRepo() throws ServiceException {
        try {
            Assert.assertTrue(employeeRepository.existsById(12L));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Test
    public void existsUserByLoginAndPassword() {
        try {
            Assert.assertTrue(userService.existsUserByUsernameAndPassword("admin","admin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
