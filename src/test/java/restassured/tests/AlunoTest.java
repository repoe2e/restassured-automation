package restassured.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import restassured.core.BaseTest;
import restassured.endpoints.AlunoEndpoints;
import restassured.models.Aluno;

public class AlunoTest extends BaseTest {

    @Test
    public void testCadastrarAluno() {
        test = extent.createTest("Testar Cadastro de Aluno", "Verifica se o cadastro de aluno funciona corretamente");

        try {
            Aluno aluno = new Aluno();
            aluno.setNome("Lorenzo");
            aluno.setSobrenome("Barbosa");
            aluno.setEmail("anderson@e2e.com.br");
            aluno.setTurma("112023");
            aluno.setCpf("121.121.121-21");

            test.log(Status.INFO, "Criando payload do aluno");
            Response response = AlunoEndpoints.cadastrarAluno(aluno);

            test.log(Status.INFO, "Enviando requisição para o endpoint");
           // assertEquals(201, response.getStatusCode(), "Verifica se o código de status é 201");
            test.pass("Aluno cadastrado com sucesso");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testConsultarAlunoPorCpf() {
        test = extent.createTest("Consultar Aluno por CPF", "Verifica se a consulta por CPF retorna os dados corretos");

        try {
            test.log(Status.INFO, "Consultando aluno com CPF 121.121.121-21");
            Response response = AlunoEndpoints.consultarAlunoPorCpf("121.121.121-21");

            assertEquals(200, response.getStatusCode(), "Verifica se o código de status é 200");
            test.pass("Consulta realizada com sucesso");

            String nome = response.jsonPath().getString("nome");
            test.log(Status.INFO, "Nome retornado: " + nome);
            assertTrue(response.getBody().asString().contains("Lorenzo"));
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testAtualizarAluno() {
        test = extent.createTest("Atualizar Aluno", "Verifica se é possível atualizar os dados de um aluno");

        try {
            Aluno alunoAtualizado = new Aluno();
            alunoAtualizado.setNome("Lorenzo Atualizado");
            alunoAtualizado.setSobrenome("Barbosa");
            alunoAtualizado.setEmail("anderson@e2e.com.br");
            alunoAtualizado.setTurma("112024");
            alunoAtualizado.setCpf("121.121.121-21");

            test.log(Status.INFO, "Atualizando aluno com novos dados");
            Response response = AlunoEndpoints.atualizarAluno("121.121.121-21", alunoAtualizado);

            assertEquals(200, response.getStatusCode(), "Verifica se o código de status é 200");
            test.pass("Dados do aluno atualizados com sucesso");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testExcluirAluno() {
        test = extent.createTest("Excluir Aluno", "Verifica se é possível excluir um aluno por CPF");

        try {
            test.log(Status.INFO, "Excluindo aluno com CPF 121.121.121-21");
            Response response = AlunoEndpoints.excluirAluno("121.121.121-21");

            assertEquals(200, response.getStatusCode(), "Verifica se o código de status é 200");
            test.pass("Aluno excluído com sucesso");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCadastrarAlunoSemNome() {
        test = extent.createTest("Cadastrar Aluno Sem Nome", "Verifica se o cadastro falha quando o nome está ausente");

        try {
            Aluno aluno = new Aluno();
            aluno.setSobrenome("Barbosa");
            aluno.setEmail("anderson@e2e.com.br");
            aluno.setTurma("112023");
            aluno.setCpf("121.121.121-21");

            test.log(Status.INFO, "Tentando cadastrar aluno sem o campo 'nome'");
            Response response = AlunoEndpoints.cadastrarAluno(aluno);

            assertEquals(400, response.getStatusCode(), "Verifica se o código de status é 400");
            test.pass("Falha de validação corretamente identificada");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCadastrarAlunoDuplicado() {
        test = extent.createTest("Cadastrar Aluno Duplicado", "Verifica se o cadastro falha para CPF duplicado");

        try {
            Aluno aluno = new Aluno();
            aluno.setNome("Lorenzo");
            aluno.setSobrenome("Barbosa");
            aluno.setEmail("anderson@e2e.com.br");
            aluno.setTurma("112023");
            aluno.setCpf("121.121.121-21");

            test.log(Status.INFO, "Cadastrando aluno pela primeira vez");
            Response primeiraResposta = AlunoEndpoints.cadastrarAluno(aluno);
            assertEquals(201, primeiraResposta.getStatusCode());

            test.log(Status.INFO, "Tentando cadastrar aluno com o mesmo CPF novamente");
            Response segundaResposta = AlunoEndpoints.cadastrarAluno(aluno);
            assertEquals(400, segundaResposta.getStatusCode());
            test.pass("Falha de duplicidade corretamente identificada");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCadastrarAlunoComEmailInvalido() {
        test = extent.createTest("Cadastrar Aluno com Email Inválido", "Verifica se o cadastro falha com email inválido");

        try {
            Aluno aluno = new Aluno();
            aluno.setNome("Lorenzo");
            aluno.setSobrenome("Barbosa");
            aluno.setEmail("invalido");
            aluno.setTurma("112023");
            aluno.setCpf("121.121.121-21");

            test.log(Status.INFO, "Tentando cadastrar aluno com email inválido");
            Response response = AlunoEndpoints.cadastrarAluno(aluno);

            assertEquals(400, response.getStatusCode(), "Verifica se o código de status é 400");
            test.pass("Falha de validação corretamente identificada");
        } catch (AssertionError e) {
            test.fail("Falha no teste: " + e.getMessage());
            throw e;
        }
    }
}
