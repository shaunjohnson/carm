package carm.exceptions

class CarmRuntimeException extends RuntimeException {
    CarmRuntimeException() {
        super()
    }

    CarmRuntimeException(String message) {
        super(message)
    }
}
