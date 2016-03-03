package com.example;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by wemstar on 2016-03-03.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataTestApplication.class)
@WebAppConfiguration
public class SpringDataTestCRUD {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("build/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void crudExampleTest() throws Exception {

        mockMvc.perform(post("/sample")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"externalId\":\"1234\",\"field1\":\"log2\",\"filed2\":\"password\"}")
        ).andExpect(status().isCreated()).andDo(document("CREATE"));

        mockMvc.perform(post("/sample")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"externalId\":\"12345\",\"field1\":\"log3\",\"filed2\":\"password\"}")
        ).andExpect(status().isCreated());

        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.sample[0].externalId",is(1234)))
                .andExpect(jsonPath("$._embedded.sample[0].field1",containsString("log2")))
                .andExpect(jsonPath("$._embedded.sample[0].filed2",containsString("password")))
                .andExpect(jsonPath("$._embedded.sample[1].externalId",is(12345)))
                .andExpect(jsonPath("$._embedded.sample[1].field1",containsString("log3")))
                .andExpect(jsonPath("$._embedded.sample[1].filed2",containsString("password")))
                .andDo(document("GET"));
        mockMvc.perform(put("/sample/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"externalId\":\"1235\",\"field1\":\"log2\",\"filed2\":\"password\"}")
        ).andExpect(status().isNoContent()).andDo(document("UPDATE"));
        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.sample[0].externalId",is(1235)))
                .andExpect(jsonPath("$._embedded.sample[0].field1",containsString("log2")))
                .andExpect(jsonPath("$._embedded.sample[0].filed2",containsString("password")))
                .andExpect(jsonPath("$._embedded.sample[1].externalId",is(12345)))
                .andExpect(jsonPath("$._embedded.sample[1].field1",containsString("log3")))
                .andExpect(jsonPath("$._embedded.sample[1].filed2",containsString("password")))
                .andDo(document("index"));


        mockMvc.perform(delete("/sample/1")).andExpect(status().isNoContent()).andDo(document("index"));
        mockMvc.perform(delete("/sample/2")).andExpect(status().isNoContent()).andDo(document("index"));
    }

}
