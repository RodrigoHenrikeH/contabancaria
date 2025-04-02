package conta;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;


public class Menu {

	public static void main(String[] args) {

		Scanner lerScanner = new Scanner(System.in);

		ContaController contas = new ContaController();

		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana Dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = lerScanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite Valores Inteiros!");
				lerScanner.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - o seu futuro começa aqui!");
				sobre();
				lerScanner.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta \n\n");

				System.out.println("Digite o numero da Agência: ");
				agencia = lerScanner.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				lerScanner.skip("\\R?");
				titular = lerScanner.nextLine();

				do {
					System.out.println("Digite o Tipo da Conta (1- CC ou 2- CP): ");
					tipo = lerScanner.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = lerScanner.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = lerScanner.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia de aniversário da Conta: ");
					aniversario = lerScanner.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}

				keyPress();
				break;

			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas Contas \n\n");
				contas.listarTodas();
				keyPress();
				break;

			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da conta - Por número \n\n");
				System.out.println("Digite o número da Conta: ");
				numero = lerScanner.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;

			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = lerScanner.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					
					System.out.println("Digite o Numero da Agência: ");
					agencia = lerScanner.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					lerScanner.skip("\\R?");
					titular = lerScanner.nextLine();
						
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = lerScanner.nextFloat();
					
					tipo = buscaConta.getTipo();
					
					switch(tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = lerScanner.nextFloat();
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
						case 2 -> {
							System.out.println("Digite o dia do Aniversario da Conta: ");
							aniversario = lerScanner.nextInt();
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}
						default ->{
							System.out.println("Tipo de conta inválido!");
						}
					}
					
				}else
					System.out.println("\nConta não encontrada!");

                keyPress();
				break;

			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta \n\n");
				
				System.out.println("Digite o número da Conta");
				numero = lerScanner.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque \n\n");
				keyPress();
				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito \n\n");
				keyPress();
				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas \n\n");
				keyPress();
				break;

			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpcção Inválida" + Cores.TEXT_RESET);
				keyPress();
				break;
			}

		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Rodrigo Henrique ");
		System.out.println("Rodrigo Henrique - rodrigos2@genstudents.org");
		System.out.println("https://github.com/RodrigoHenrikeH");
		System.out.println("*********************************************************");

	}

	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {
			System.out.println("Você digitou uma tecla diferente de Enter!");
		}
	}

}
