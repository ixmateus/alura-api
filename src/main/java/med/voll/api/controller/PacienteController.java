package med.voll.api.controller;

import med.voll.api.paciente.DadosPaciente;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {
    public void cadastrar(@RequestBody DadosPaciente dados) {
        System.out.println("Dados recebidos: " + dados);
    }

}
