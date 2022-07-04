import java.time.Instant

data class Event(val id: Long, val timestamp: Instant = Instant.now(), val message: String  )
