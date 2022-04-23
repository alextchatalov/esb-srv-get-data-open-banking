package com.getdata.dataprovider.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ReferentialRateIndexer {

    SEM_INDEXADOR_TAXA,
    PRE_FIXADO,
    POS_FIXADO_TR_TBF,
    POS_FIXADO_TJLP,
    POS_FIXADO_LIBOR,
    POS_FIXADO_TLP,
    OUTRAS_TAXAS_POS_FIXADAS,
    FLUTUANTES_CDI,
    FLUTUANTES_SELIC,
    OUTRAS_TAXAS_FLUTUANTES,
    INDICES_PRECOS_IGPM,
    INDICES_PRECOS_IPCA,
    INDICES_PRECOS_IPCC,
    OUTROS_INDICES_PRECO,
    CREDITO_RURAL_TCR_PRE,
    CREDITO_RURAL_TCR_POS,
    CREDITO_RURAL_TRFC_PRE,
    CREDITO_RURAL_TRFC_POS,
    OUTROS_INDEXADORES,
    PRE_FIXADO_TR_TBF,
    NAO_APLICAVEL;

    public static ReferentialRateIndexer get(String referentialRateIndexer) {
        try {
            return ReferentialRateIndexer.valueOf(referentialRateIndexer);
        } catch (Exception e) {
            log.warn("ReferentialRateIndexer not found: {} Setting to NAO_APLICAVEL", referentialRateIndexer);
            return NAO_APLICAVEL;
        }
    }

}
