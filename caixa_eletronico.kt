class CaixaEletronico(nome: String, idade: Int){
    private var nome: String = ""
    private var idade: Int = 0
    private var dinheiroConta = 0.0
    private var password: Int = 3589
    private var extrato: String = ""

    init {
        println("Parabéns graças a sua criação da conta vai receber um bonus de 50 reais :D")
        dinheiroConta += 50
        this.nome = nome
        this.idade = idade
    }
    fun getName(): String{
        return nome
    }

    fun saqueMoney(): Double{
        var verif = verification()
        if (verif) {
            print("quanto quer sacar: ")
            var quantSacar = readln().toDouble()
            while (quantSacar <= 0){
                println("Valor invalido...")
                print("Digite um valor valido: ")
                quantSacar = readln().toDouble()
            }
            if (quantSacar <= dinheiroConta){
                dinheiroConta -= quantSacar
                return quantSacar
            } else {println("saldo insuficiente...")}
        }
        return 0.0
    }
    private fun verSaldoSystema(): Double{
        return dinheiroConta
    }

    private fun verification(): Boolean{
        print("Digite a senha: ")
        var senha:Int = readln().toInt()

        if (senha == password){
            return true
        } else {
            for (n in 3 downTo 1) {
                println("Restam apenas $n tentativas...")
                print("Digite a senha: ")
                senha = readln().toInt()
                if (senha == password) {
                    return true
                }
            }
                println("Cancelado o processo...")
                return false
        }
    return false
    }
    fun verSaldo(): Double{
        var verif = verification()
        if (verif) {
            return dinheiroConta
        } else {
            print("Cancelado...")
            return 0.0
        }
    }

    fun deposito(valor: Double){
        val verify: Boolean = verification()
        if (verify) {
            if (valor > 0.0) {
                dinheiroConta += valor
                println("Transferencia completa...")
                addExtrato("Deposito", valor)
            } else {
                println("Valor invalido")
            }
        } else {print("Cancelado...")}
    }


    fun transferenciaExternaReceber(valor: Double, nome: String) {

        dinheiroConta += valor
        print("Valor recebido")
        addExtrato("Dinheiro enviado do remetente: $nome", valor)

    }
    fun addExtrato(tipoServico: String, valor: Double){

        extrato += "$tipoServico - valor: $valor - Saldo atual: $dinheiroConta \n"

    }
    fun getExtrato():  String{
        return extrato
    }
     fun transferenciaExternaEnviar(): Double {
         println("Faça a verificação: ")
         val verify: Boolean = verification()

         if (verify) {
             print("Digite o valor a ser enviado: ")
             var valorParaEnviar = readln().toDouble()
             var cond: Boolean = true

             while (cond) {

                 if (dinheiroConta > 0.0) {
                     if (valorParaEnviar < dinheiroConta) {
                         println("Certo...")
                         dinheiroConta -= valorParaEnviar
                         addExtrato("Envio de Dinheiro", valorParaEnviar)
                         return valorParaEnviar
                     } else {
                         println("Sem saldo Suficiente...")
                         println("Saldo Atual: ${verSaldoSystema()}")
                     }
                 } else {
                     println("Sem saldo")
                     return 0.0
                 }

                 print("Deseja cancelar envio? (1-Sim, 2-Não): ")
                 var escolha: Int = readln().toInt()

                 while (escolha !in 1..2) {
                     println("Valor invalido")
                     print("Deseja cancelar envio? (1-Sim, 2-Não): ")
                     escolha = readln().toInt()
                 }
                 when (escolha) {
                     1 -> {
                         print("Cancelado...")
                         cond = false

                     }

                     2 -> {
                         print("Digite um novo valor: ")
                         valorParaEnviar = readln().toDouble()
                     }
                 }

             }
         }
         return 0.0

    }




}