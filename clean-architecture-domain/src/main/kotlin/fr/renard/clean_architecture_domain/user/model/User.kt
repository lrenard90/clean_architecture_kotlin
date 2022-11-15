package fr.renard.clean_architecture_domain.user.model

import fr.renard.clean_architecture_domain.socle.model.ModelSnapshot

class User(val id: Long, val email: UserEmail, private val password: UserPassword): ModelSnapshot<UserState> {

    constructor(userState: UserState) : this(userState.id, UserEmail(userState.email), UserPassword(userState.password)) {
    }

    override fun toSnapshot(): UserState {
        return UserState(id, email.value, password.value)
    }

}