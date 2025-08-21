public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    public static Command fromString(String input) throws FattyException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FattyException("Unknown command: " + input);
        }
    }
}
