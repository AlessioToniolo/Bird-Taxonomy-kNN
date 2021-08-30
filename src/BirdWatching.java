import java.util.ArrayList;

public class BirdWatching {
    public ArrayList<Bird> birds;
    public ArrayList<Bird> unknowns;

    public BirdWatching(ArrayList<Bird> birds, ArrayList<Bird> unknowns) {
        this.birds = birds;
        this.unknowns = unknowns;
    }

    public Double getDistance(Bird start, Bird end) {
        double length = Math.pow(end.length - start.length, 2);
        double width = Math.pow(end.width - start.width, 2);
        double wingSpan = Math.pow(end.wingSpan - start.wingSpan, 2);
        double wingAngle = Math.pow(end.wingAngle - start.wingAngle, 2);
        return Math.sqrt(length + width + wingSpan + wingAngle);
    }

    public ArrayList<Double> getDistances(Bird start) {
        ArrayList<Double> out = new ArrayList<>();
        for (int i = 0; i < birds.size(); i++) {
            out.add(getDistance(start, birds.get(i)));
        }
        return out;
    }

    public String voter(Bird start, int k) {
        ArrayList<Bird> candidates = new ArrayList<Bird>();
        ArrayList<Double> distances = getDistances(start);
        double closestDistance = Double.POSITIVE_INFINITY;
        Bird closestPoint = null;
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) < closestDistance) {
                closestDistance = distances.get(i);
            }
        }
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) == closestDistance) {
                candidates.add(birds.get(i));
            }
        }
        for (int i = 0; i < distances.size(); i++) {
            if (candidates.size() < k) {
                candidates.add(birds.get(i));
            } else {
                for (int l = 0; l < candidates.size(); l++) {
                    if (distances.get(i) > getDistance(start, candidates.get(l))) {
                        candidates.set(l, birds.get(i));
                        break;
                    }
                }
            }
        }

        // Find president
        int Accipitridae = 0;
        int Cathartidae = 0;
        int Passeridae = 0;

        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).label.equals("Accipitridae")) {
                Accipitridae++;
            } else if (candidates.get(i).label.equals("Cathartidae")) {
                Cathartidae++;
            } else {
                Passeridae++;
            }
        }

        int leadingAmount = 0;
        String leadingLabel = "";
        ArrayList<Vote> labels = new ArrayList<Vote>();
        labels.add(new Vote("Accipitridae", Accipitridae));
        labels.add(new Vote("Cathartidae", Cathartidae));
        labels.add(new Vote("Passeridae", Passeridae));

        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).votes > leadingAmount) {
                leadingAmount = labels.get(i).votes;
                leadingLabel = labels.get(i).label;
            } else if (labels.get(i).votes == leadingAmount) {
                leadingLabel = "Tie";
            }
        }

        return leadingLabel;
    }
}
