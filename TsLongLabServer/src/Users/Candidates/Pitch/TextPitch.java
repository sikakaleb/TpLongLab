package Users.Candidates.Pitch;


import commonInterfaces.IPitch;

// Implémentation de Pitch pour le texte
public class TextPitch implements IPitch {
    private static final long serialVersionUID = 1L;
    private String content;

    public TextPitch() {
    }

    public TextPitch(String content) {
        this.content = content;
    }
    @Override
    public String getPitchContent() {
        return content;
    }
}
