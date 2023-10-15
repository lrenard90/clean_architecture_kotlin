package fr.renard.clean_architecture_domain.socle.model

interface Snapshotable<DOMAIN_STATE_TYPE> {

    fun data(): DOMAIN_STATE_TYPE

}