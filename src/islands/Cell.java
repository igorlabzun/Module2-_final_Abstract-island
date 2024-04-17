package islands;

import abstraction.animal.Animal;
import interfaces.Organism;

import java.util.Set;

public class Cell {
    private Set<Organism> residents;

    public Cell(Set<Organism> residents) {
        this.residents = residents;
    }

    public Set<Organism> getResidents() {

        return residents;
    }

    public void setResidents(Set<Organism> newResidents) {
        this.residents = newResidents;
    }

    public void removeAnimal(Animal animal) {


    }

    public void addAnimal(Animal animal) {

    }
}
