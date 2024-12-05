package restassured.endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import restassured.models.Aluno;

public class AlunoEndpoints {

	// Cadastrar novo aluno 
    public static Response cadastrarAluno(Aluno aluno) {
        return given()
                .contentType("application/json")
                .body(aluno)
                .when()
                .post("/api/alunos");
    }

	// Consultar aluno por CPF
	public static Response consultarAlunoPorCpf(String cpf) {
		return given().when().get("/api/alunos/" + cpf);
	}

	// Atualizar dados de um aluno
	public static Response atualizarAluno(String cpf, Aluno aluno) {
		return given().contentType("application/json").body(aluno).when().put("/api/alunos/" + cpf);
	}

	// Excluir aluno
	public static Response excluirAluno(String cpf) {
		return given().when().delete("/api/alunos/" + cpf);
	}

	// Listar todos os alunos
	public static Response listarTodosAlunos() {
		return given().when().get("/api/alunos");
	}
}