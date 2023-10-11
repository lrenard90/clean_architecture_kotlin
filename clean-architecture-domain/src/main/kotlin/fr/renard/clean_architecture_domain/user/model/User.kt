package fr.renard.clean_architecture_domain.user.model

import fr.renard.clean_architecture_domain.socle.model.Snapshotable

class User(val id: Long, val email: UserEmail, private val password: UserPassword): Snapshotable<UserState> {

    constructor(userState: UserState) : this(userState.id, UserEmail(userState.email), UserPassword(userState.password)) {
    }

    override fun snapshot(): UserState {
        return UserState(id, email.value, password.value)
    }

}