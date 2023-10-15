package fr.renard.clean_architecture_application.socle.data

interface Snapshotable<DOMAIN_DATA_TYPE> {

    fun data(): DOMAIN_DATA_TYPE

}