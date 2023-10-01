package Users.Candidates.Pitch;


import commonInterfaces.IPitch;

class VideoPitch implements IPitch {
    private String videoURL;

    public VideoPitch(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public String getPitchContent() {
        return videoURL;
    }
}