package com.getdata.dataprovider.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RequiredWarranties {

    CESSAO_DIREITOS_CREDITORIOS("o cedente transfere ao credor/cessionário a titularidade de direitos creditórios, até a liquidação da dívida. O credor/cessionário passa a recebê-los diretamente dos devedores e credita o produto da operação para o cedente na operação que originou a cessão, até a sua liquidação"),
    CAUCAO("garantia instituída sobre créditos do garantidor"),
    PENHOR("direito real que consiste na tradição de uma coisa móvel ou mobilizável, suscetível de alienação, realizada pelo devedor ou por terceiro ao credor, a fim de garantir o pagamento do débito"),
    ALIENACAO_FIDUCIARIA("transferência ao credor, ou fiduciário, da propriedade do bem"),
    HIPOTECA("direito real de garantia que afeta um bem imóvel para o cumprimento da obrigação"),
    OPERACOES_GARANTIDAS_PELO_GOVERNO("nas instâncias federal, estadual ou municipal"),
    OUTRAS_GARANTIAS_NAO_FIDEJUSSORIAS("as garantias reais não descritas como: cessão de direitos creditórios, caução, penhor, alienação fiduciária, hipoteca ou operação garantida pelo governo"),
    SEGUROS_ASSEMELHADOS("os seguros (e assemelhados) contratados para garantir o pagamento da operação em circunstâncias adversas"),
    GARANTIA_FIDEJUSSORIA("baseada na fidelidade do garantidor em cumprir as obrigações, caso o devedor não o faça"),
    BENS_ARRENDADOS("bem objeto do arrendamento financeiro"),
    GARANTIAS_INTERNACIONAIS("declarar se a garantia é mitigadora ou não, observados os critérios definidos pela Circular 3.644, de 4 de março de 2013"),
    OPERACOES_GARANTIDAS_OUTRAS_ENTIDADES("declarar as garantias prestadas pelas entidades descritas no item 3. Informações de Garantias (i) do documento 3040 - Bacen"),
    ACORDOS_COMPENSACAO("operações que sejam abrangidas por acordos para a compensação e liquidação de obrigações no âmbito do SFN, nos termos da Resolução 3.263, de 24 de fevereiro de 2005"),
    NAO_APLICAVEL("Não Aplicável");
    private String description;

    RequiredWarranties(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static RequiredWarranties get(String warranties) {

        try {
            if (warranties.equalsIgnoreCase("NA")) {
                return NAO_APLICAVEL;
            } else {
                return RequiredWarranties.valueOf(warranties);
            }
        } catch (Exception e) {
            log.warn("Warranties not found: " + warranties + " Setting to NAO_APLICAVEL");
            return NAO_APLICAVEL;
        }
    }

}
