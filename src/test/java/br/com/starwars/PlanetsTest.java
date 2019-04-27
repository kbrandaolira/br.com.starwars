package br.com.starwars;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.starwars.models.Planets;
import br.com.starwars.repositories.PlanetsRepository;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PlanetsTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlanetsRepository planetsRepository;
	
	private List<Planets> planets;
	
	@Before
	public void before() {
		
		Planets planet = new Planets(
				"5cc287459f50ba273cc5be04",
				"Hoth",
				"frozen",
				"tundra"
			);
		
		this.planets = Arrays.asList(planet);
		
	}
	
	@Test
	public void create() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
		        .post("/v0/planets/")
		        .accept(MediaType.APPLICATION_JSON)
		        .content("{\"_id\":\"5cc287459f50ba273cc5be04\",\"name\":\"Hoth\",\"climate\":\"frozen\",\"terrain\":\"tundra\"}\"")
		        .contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(request)
		        .andExpect(status().isCreated()).andReturn();
			
	}
	
	@Test
	public void findAll() throws Exception {
		given(this.planetsRepository.findAll()).willReturn(planets);
		this.mockMvc.perform(get("/v0/planets/"))
	        .andExpect(status().isOk())
	        .andExpect(content().json("[{\"_id\":\"5cc287459f50ba273cc5be04\",\"name\":\"Hoth\",\"climate\":\"frozen\",\"terrain\":\"tundra\",\"idSwapi\":0,\"moviesAppearances\":-1}]"));
			
	}
	
	@Test
	public void findByName() throws Exception {
		given(this.planetsRepository.findByName("Hoth")).willReturn(planets.get(0));
		this.mockMvc.perform(get("/v0/planets/search?name=Hoth"))
	        .andExpect(status().isOk())
	        .andExpect(content().json("{\"_id\":\"5cc287459f50ba273cc5be04\",\"name\":\"Hoth\",\"climate\":\"frozen\",\"terrain\":\"tundra\",\"idSwapi\":0,\"moviesAppearances\":-1}"));
			
	}
	
	@Test
	public void findBy_id() throws Exception {
		given(this.planetsRepository.findBy_id(new ObjectId("5cc287459f50ba273cc5be04"))).willReturn(planets.get(0));
		this.mockMvc.perform(get("/v0/planets/5cc287459f50ba273cc5be04"))
	        .andExpect(status().isOk())
	        .andExpect(content().json("{\"_id\":\"5cc287459f50ba273cc5be04\",\"name\":\"Hoth\",\"climate\":\"frozen\",\"terrain\":\"tundra\",\"idSwapi\":0,\"moviesAppearances\":-1}"));
			
	}
	
	@Test
	public void delete() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
			 .delete("/v0/delete")
		     .contentType(MediaType.APPLICATION_JSON))
			 .andExpect(status().isNotFound());
			
	}
}
