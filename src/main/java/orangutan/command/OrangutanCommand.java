package orangutan.command;

import orangutan.OrangutanException;

interface OrangutanCommand {
    String run(OrangutanContext context) throws OrangutanException;
}
