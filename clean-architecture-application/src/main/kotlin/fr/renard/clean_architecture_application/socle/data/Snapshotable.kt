package fr.renard.clean_architecture_application.socle.data

interface Snapshotable<DOMAIN_STATE_TYPE> {

    fun data(): DOMAIN_STATE_TYPE

}