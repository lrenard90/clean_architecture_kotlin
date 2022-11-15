package fr.renard.clean_architecture_domain.socle.model

interface ModelSnapshot<DOMAIN_STATE_TYPE> {

    fun toSnapshot(): DOMAIN_STATE_TYPE

}