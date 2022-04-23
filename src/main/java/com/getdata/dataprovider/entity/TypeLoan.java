package com.getdata.dataprovider.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum TypeLoan {

    EMPRESTIMO_MICROCREDITO_PRODUTIVO_ORIENTADO("Empréstimo-Microcrédito Produtivo Orientado - segundo PNMPO é o crédito concedido para financiamento das atividades produtivas, cuja metodologia será estabelecida em regulamento, observada a preferência do relacionamento direto com os empreendedores, admitido o uso de tecnologias digitais e eletrônicas que possam substituir o contato presencial"),
    EMPRESTIMO_CHEQUE_ESPECIAL("Empréstimo-Cheque especial - operações de crédito vinculadas à conta corrente, nas quais determinado limite de crédito é disponibilizado aos clientes para utilização de acordo com suas conveniências, sem necessidade de comunicação prévia à instituição financeira"),
    EMPRESTIMO_CONTA_GARANTIDA("Empréstimo-Conta garantida - operações de crédito vinculadas à conta corrente, nas quais determinado limite de crédito é disponibilizado aos clientes para utilização de acordo com suas conveniências, sem necessidade de comunicação prévia à instituição financeira"),
    EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS("Empréstimo-Capital de giro com prazo de vencimento até 365 dias: operações de crédito voltadas para o financiamento de curto prazo (igual ou inferior a 365 dias) das pessoas jurídicas, vinculadas às necessidades de capital de giro e a um contrato específico que estabeleça prazos, taxas e garantias"),
    EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_SUPERIOR_365_DIAS("Empréstimo-Capital de giro com prazo de vencimento superior a 365 dias: operações de crédito voltadas para o financiamento de curto prazo (maior que 365 dias) das pessoas jurídicas, vinculadas às necessidades de capital de giro e a um contrato específico que estabeleça prazos, taxas e garantias"),
    EMPRESTIMO_CAPITAL_GIRO_ROTATIVO("Empréstimo-Capital de giro rotativo: operações de crédito voltadas para o financiamento de capital de giro, vinculadas a um contrato que estabeleça linha de crédito rotativo, de forma que, à medida que a empresa devedora amortize os empréstimos já tomados, o limite disponível para utilização seja restituído, e amortizações com datas predeterminadas, podendo ser facultado ao devedor repactuar o fluxo de pagamentos ao longo da vigência do contrato"),
    EMPRESTIMO_CREDITO_PESSOAL_CONSIGNADO("operações de crédito com retenção de parcela do salário ou benefício do tomador, para o pagamento das prestações do empréstimo – desconto em folha de pagamento – nos termos da legislação em vigor"),
    EMPRESTIMO_CREDITO_PESSOAL_SEM_CONSIGNACAO("operações de empréstimos às pessoa natural, sem vinculação com aquisição de bem ou serviço e sem retenção de parcela do salário ou benefício do tomador para o pagamento das prestações do empréstimo"),
    EMPRESTIMO_HOME_EQUITY("empréstimos pessoa natural, garantidos por hipoteca ou por alienação fiduciária de bens imóveis residenciais, sem vinculação a aquisição de bens"),
    EMPRESTIMO_CAPITAL_DE_GIRO_PESSOA_UNICA("N/A"),
    CHEQUE_EMPRESARIAL_PJ("N/A"),
    NAO_APLICAVEL("N/A");

    private String description;

    TypeLoan(String description) {
        this.description = description;
    }

    public static TypeLoan get(String type) {

        try {
            return TypeLoan.valueOf(type);
        } catch (Exception e) {
            log.warn("Type Loan not found: " + type + " Setting to NAO_APLICAVEL");
            return NAO_APLICAVEL;
        }
    }

    public String getDescription() {
        return description;
    }
}
