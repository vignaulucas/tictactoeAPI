package com.postegresql.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postegresql.model.Game;
import com.postegresql.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private ObjectMapper objectMapper = new ObjectMapper();


	@Test
	void contextLoads() {
	}

	@Test
	public void testNewGame() throws Exception {
		Game game = new Game(); // Configurez votre objet Game selon vos besoins
		when(gameService.createGame(any(Game.class))).thenReturn(game);

		mockMvc.perform(post("/api/game/newGame")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(game)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(game.getId())); // Assurez-vous que votre objet Game a un getter pour l'id

		verify(gameService, times(2)).createGame(any(Game.class)); // Vous appelez deux fois createGame dans votre méthode
	}

	@Test
	public void testGetAllGames() throws Exception {
		List<Game> allGames = List.of(new Game(), new Game()); // Configurez vos jeux comme nécessaire
		when(gameService.getAllGames()).thenReturn(allGames);

		mockMvc.perform(get("/api/game/allGames"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(allGames.size()));

		verify(gameService).getAllGames();
	}





}
