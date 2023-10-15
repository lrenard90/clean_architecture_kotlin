package fr.renard.clean_architecture_application.authentication.domain

import fr.renard.clean_architecture_application.socle.data.Snapshotable

class User(val id: Long, val email: UserEmail, private val password: UserPassword): Snapshotable<UserState> {

    constructor(userState: UserState) : this(userState.id, UserEmail(userState.email), UserPassword(userState.password)) {
    }

    override fun data(): UserState {
        return UserState(id, email.value, password.value)
    }

}