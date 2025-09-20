//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.copilot;

import java.util.List;

public class CopilotResponseDTO {
private List<CandidateDTO> candidates;

    public List<CandidateDTO> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateDTO> candidates) {
        this.candidates = candidates;
    }

    public static class CandidateDTO {
        private String finishReason;
        private int index;
        private List<PartDTO> content;

        public String getFinishReason() {
            return finishReason;
        }

        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<PartDTO> getContent() {
            return content;
        }

        public void setContent(List<PartDTO> content) {
            this.content = content;
        }
    }

    public static class PartDTO {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
