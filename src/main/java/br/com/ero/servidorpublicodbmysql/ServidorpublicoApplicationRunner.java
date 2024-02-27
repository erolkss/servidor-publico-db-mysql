package br.com.ero.servidorpublicodbmysql;

import javax.swing.JOptionPane;

import br.com.ero.servidorpublicodbmysql.entity.ServidorPublico;
import br.com.ero.servidorpublicodbmysql.service.ServidorPublicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@Configuration
public class ServidorpublicoApplicationRunner implements CommandLineRunner {

    private ServidorPublicoService servidorService;

    @Autowired
    public void setServidorPublicoService(ServidorPublicoService servidorService) {
        this.servidorService = servidorService;
    }

    @PostConstruct
    public void listAll() {
        List<ServidorPublico> servidorespublicos = servidorService.listAll();

        if (servidorespublicos.size() != 0) {
            System.out.println("###################################");
            servidorespublicos.forEach(
                    servidor -> {
                        System.out.println(servidor.getMatricula());
                        System.out.println(servidor.getNome());
                        System.out.println(servidor.getFoto());
                        System.out.println(servidor.getOrgao());
                    }
            );

        } else {
            System.out.println("Sem Registros");
        }
    }

    @PostConstruct
    public void listByMatricula() {
        long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula procurada"));
        Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

        if (servidorEncontrado.isPresent()) {
            System.out.println("###################################");
            System.out.println(servidorEncontrado.get().getMatricula());
            System.out.println(servidorEncontrado.get().getNome());
            System.out.println(servidorEncontrado.get().getFoto());
            System.out.println(servidorEncontrado.get().getOrgao());
        } else
            System.out.println("Não existe o servidor público com a matrícula informada");

    }

    @PostConstruct
    public void save() {
        long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula do Novo Servidor:"));
        Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

        if (!servidorEncontrado.isPresent()) {
            ServidorPublico novoServidorPublico = new ServidorPublico();
            novoServidorPublico.setMatricula(matricula);

            String nome = JOptionPane.showInputDialog("Digite o Nome do Novo Servidor");
            novoServidorPublico.setNome(nome);
            String foto = JOptionPane.showInputDialog("Digite a foto do Novo Servidor");
            novoServidorPublico.setFoto(foto);
            String orgao = JOptionPane.showInputDialog("Digite o Orgão do Novo Servidor");
            novoServidorPublico.setOrgao(orgao);
            String vinculo = JOptionPane.showInputDialog("Digite o Vínculo do Novo Servidor");
            novoServidorPublico.setVinculo(vinculo);
            String cargo = JOptionPane.showInputDialog("Digite o Cargo do Novo Servidor");
            novoServidorPublico.setCargo(cargo);
            String lotacao = JOptionPane.showInputDialog("Digite a Lotação do Novo Servidor");
            novoServidorPublico.setLotacao(lotacao);
            String exercicio = JOptionPane.showInputDialog("Digite o Exercício do Novo do Servidor");
            novoServidorPublico.setExercicio(exercicio);
            String email = JOptionPane.showInputDialog("Digite o email do Novo Servidor");
            novoServidorPublico.setEmail(email);
            String telefone = JOptionPane.showInputDialog("Digite o Telefone do Novo Servidor");
            novoServidorPublico.setTelefone(telefone);
            String celular = JOptionPane.showInputDialog("Digite o Celular do Novo Servidor");
            novoServidorPublico.setCelular(celular);
            String cpf = JOptionPane.showInputDialog("Digite o Cpf do Novo Servidor");
            novoServidorPublico.setCpf(cpf);
            String naturalidade = JOptionPane.showInputDialog("Digite a Naturalidade do Novo Servidor");
            novoServidorPublico.setNaturalidade(naturalidade);


            servidorService.save(novoServidorPublico);
        } else {
            System.out.println("Servidor Público já existe");
        }


    }

    @PostConstruct
    public void update() {
        long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula do Servidor a ser Alterado:"));
        Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

        if (servidorEncontrado.isPresent()) {
            String nome = JOptionPane.showInputDialog("Digite o Nome do Novo Servidor");
            servidorEncontrado.get().setNome(nome);

            servidorService.update(servidorEncontrado.get());
        } else {
            System.out.println("Sevidor Não Encontrado");
        }

    }

    @PostConstruct
    public void delete() {
        long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula do Servidor a ser Excluído:"));
        Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

        if (servidorEncontrado.isPresent()) {
            servidorService.delete(matricula);
        } else {
            System.out.println("Sevidor Não Encontrado");
        }

    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

    }

}
