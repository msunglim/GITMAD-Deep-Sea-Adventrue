package club.gitmad.deepseaadventure

class Game(players: Array<Player>) {
    var players = players
    private var round:Int = 1

    fun get_players():Array<Player> {
        return players
    }

    fun get_round():Int {
        return round
    }

    fun inc_round() {
        round++
    }


}

