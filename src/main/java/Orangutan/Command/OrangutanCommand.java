package Orangutan.Command;

import Orangutan.OrangutanException;

interface OrangutanCommand {
    String run(OrangutanContext context) throws OrangutanException;
}
