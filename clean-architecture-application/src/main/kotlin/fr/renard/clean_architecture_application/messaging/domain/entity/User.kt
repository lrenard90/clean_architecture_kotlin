package fr.renard.clean_architecture_application.messaging.domain.entity

class User(val name: String) {
    val subscriptions: MutableList<String> = mutableListOf()

    fun follows(userToFollow: String) {
        subscriptions.add(userToFollow)
    }
}