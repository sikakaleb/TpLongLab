package Users.Candidates.Pitch;


import commonInterfaces.IPitch;

// Implémentation de Pitch pour le texte
public class TextPitch implements IPitch {
    private String content;

    public TextPitch(String content) {
        this.content = content;
    }
    @Override
    public String getPitchContent() {
        return content;
    }
}
