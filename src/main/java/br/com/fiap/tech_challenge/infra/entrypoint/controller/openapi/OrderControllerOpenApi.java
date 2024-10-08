package br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.OrderWorkItemsResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.handler.ErrorsValidateData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "Order")
public interface OrderControllerOpenApi {

	@Operation(summary = "Find Work Items")
	@ApiResponse(responseCode = "200", description = "Ok Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "OrderWorkItemsResponseDTO")))
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<OrderWorkItemsResponseDTO> findWorkItems();

	@Operation(summary = "Register a Order")
	@ApiResponse(responseCode = "201", description = "Created Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "CreateOrderResponseDTO")))
	@ApiResponse(responseCode = "400", description = "Bad Request Response",
			content = @Content(mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<CreateOrderResponseDTO> create(CreateOrderRequestDTO orderRequest);

	@Operation(summary = "Get the status of an order by its ID")
	@ApiResponse(responseCode = "200", description = "OK Response",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)))
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<Boolean> isOrderPaid(UUID id);

	@Operation(summary = "Evolve Status of Orders")
	@ApiResponse(responseCode = "204", description = "OK Response")
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<?> evolveStatus(@PathVariable("id") final UUID id);

}
