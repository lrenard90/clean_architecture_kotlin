package fr.renard.clean_architecture_domain.socle.model

interface Snapshotable<DOMAIN_STATE_TYPE> {

    fun snapshot(): DOMAIN_STATE_TYPE

}