package br.com.roberto.advanced.advancedtechniques.rest.documentation;

import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentInputDto;
import br.com.roberto.advanced.advancedtechniques.rest.dto.StudentOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Student", description = "Student Resources")
@RequestMapping(value = "/api/v1")
public interface StudentController {

    @Operation(summary = "Return all Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students Found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentOutputDto.class))}),
//            @ApiResponse(responseCode = "400", description = "Clientes nao encontrados",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Void.class))}),
//            @ApiResponse(responseCode = "500", description = "Erro interno",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Void.class))}),
//            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudentOutputDto>> getAllStudents();

}
