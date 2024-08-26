package hora_de_codar_5_kotlin
import CaixaEletronico
var contas = mutableListOf<CaixaEletronico>()

fun main() {
    while (true) {

        println("Bem vindo ao caixa eletronico")
        print(
            """
        1-Criar Conta
        2-Listar contas
        3-Entrar na conta
        
    escolha: """.trimIndent()
        )
        var escolha: Int = readln().toInt()

        when (escolha) {
            1 -> {
                criarConta(contas)
                println("Criado com Sucesso")
            }

            2 -> {
                print(listarContas(contas))
            }

            3 -> {
                escolhaConta(contas)

            }
        }

    }
}
fun escolhaConta(contas: MutableList<CaixaEletronico>){
    print(listarContas(contas))
    while(true) {
        print("Escolha(em numero correspondente): ")
        var escolha = readln().toInt()
        if (escolha in 1..contas.size) {
            mainAccount(contas[escolha - 1], contas)
        } else {
            print("valor invalido")
        }
        print("Deseja continuar(S,N): ")
        var continuar: String = readln()

        if (continuar.equals("n", ignoreCase = true)){
            main()
        }else{
            println("Certo")
        }
    }
}

fun mainAccount(conta: CaixaEletronico, contas: MutableList<CaixaEletronico>){
    var cond: Boolean = true
    while (cond){
    print("""
        1- Deposito
        2- transferir valor
        3- extrato
        4-Saque
        5-Saldo
        6-Voltar
        
      
    escolha: """.trimIndent())
        var escolha:Int = readln().toInt()
        when (escolha){
            1 -> {
                print("Digite um valor para depositar: ")
                val valor_deposito: Double = readln().toDouble()
                conta.deposito(valor_deposito)
            }
            2-> {
                println(listarContas(contas))
                print("Digite qual pessoa voce quer transferir(inteiro): ")
                var pessoatranf: Int = readln().toInt()
                while (pessoatranf !in 1..contas.size) {
                    println("Digite um valor valido...")
                    println(listarContas(contas))
                    print("Digite qual pessoa voce quer transferir(inteiro): ")
                    pessoatranf = readln().toInt()

                }
                contas[pessoatranf-1].transferenciaExternaReceber(conta.transferenciaExternaEnviar(), conta.getName())
                print("Pagamento enviado para ${contas[pessoatranf-1].getName()}")
            }
            3 -> {
                print(conta.getExtrato())

            }
            4 -> {
                println("valor sacado: ${conta.saqueMoney()}")
            }
            5 -> {
                println(conta.verSaldo())
            }
            6 -> main()

        }
    }

}

fun criarConta(contas: MutableList<CaixaEletronico>){
    print("Digite o nome: ")
    var nome: String = readln()
    print("Digite sua idade: ")
    var idade: Int = readln().toInt()
    contas.add(CaixaEletronico(nome, idade))
}
//fun extrato(){
//
//}
fun listarContas(contas: MutableList<CaixaEletronico>): String{

    var big_string = ""
    var quant_listas = contas.size
    for (conta in 1..quant_listas){
        big_string += "${conta}- Nome: ${contas[conta-1].getName()} \n"
    }
    return big_string
}



