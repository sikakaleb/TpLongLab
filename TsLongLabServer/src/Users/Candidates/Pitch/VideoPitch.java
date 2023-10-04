package Users.Candidates.Pitch;


import commonInterfaces.IPitch;

class VideoPitch implements IPitch {
    private static final long serialVersionUID = 1L;
    private String videoURL;

    public VideoPitch() {
    }
    public VideoPitch(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public String getPitchContent() {
        return videoURL;
    }
}