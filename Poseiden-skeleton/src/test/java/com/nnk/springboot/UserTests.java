package com.nnk.springboot;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserController userController;
	@Autowired 
	private WebApplicationContext context;
	
	static User userTests = new User();
	@Before
	public  void setUp() {
		 
		mockMvc = MockMvcBuilders
			    .webAppContextSetup(context)
			    .apply(springSecurity())
			    .build();
	}
	
	@Test
	@Transactional
	public void TestUser() throws Exception {
		System.out.println("Ca passe");
		userTests.setFullname("FullName");
		userTests.setPassword("Password");
		userTests.setRole("ADMIN");
		userTests.setUsername("Username");
		
		String userTests ="{\r\n"
				+ "    \"username\" : \"username\",\r\n"
				+ "    \"password\": \"Password@1234\",\r\n"
				+ "    \"fullname\":\"fullname1\",\r\n"
				+ "    \"role\":\"ADMIN\"\r\n"
				+ "\r\n"
				+ "}";
		// SIGN UP 
		/*MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));*/
        
		MockHttpServletRequestBuilder req =post("/user/validate").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(userTests);
		 this.mockMvc.perform(req).andExpect((redirectedUrl("/user/list")));
		 
	}
	@Test
	public void connection() throws Exception {
		this.mockMvc.perform(get("/app/login")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void getUserList() throws Exception {
		this.mockMvc.perform(formLogin("/app/login").user("test").password("test")).andExpect(authenticated());
	}
	@Test
	public void contextLoad() throws Exception {
		assertThat(userController).isNotNull();
		this.mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isOk());
	}
	@Test @WithMockUser(username="test", password = "test")
	public void allRequest() throws Exception {
		String bidList = "{\r\n"
				+ "    \"account\" : \"account\",\r\n"
				+ "     \"type\": \"type\",\r\n"
				+ "      \"bidQuantity\":20"
				+ "}";
		BidList bid=  new BidList("Account Test", "Type Test", 10d);
		MockHttpServletRequestBuilder req =post("/bidList/validate").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8).content(bidList);
		this.mockMvc.perform(get("/user/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/bidList/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/curvePoint/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/bidList/add")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/curvePoint/add")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/rating/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/rating/add")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/ruleName/add")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/trade/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/trade/add")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/ruleName/list")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(req).andExpect(status().isOk());
		
		
	}
}


