package com.performance.persist.domain;

public class ScientificResearch {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scientific_research.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scientific_research.sci_content
     *
     * @mbggenerated
     */
    private String sciContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scientific_research.sci_project
     *
     * @mbggenerated
     */
    private String sciProject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scientific_research.sci_grade
     *
     * @mbggenerated
     */
    private String sciGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scientific_research.sci_score
     *
     * @mbggenerated
     */
    private Integer sciScore;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scientific_research.id
     *
     * @return the value of scientific_research.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scientific_research.id
     *
     * @param id the value for scientific_research.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scientific_research.sci_content
     *
     * @return the value of scientific_research.sci_content
     *
     * @mbggenerated
     */
    public String getSciContent() {
        return sciContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scientific_research.sci_content
     *
     * @param sciContent the value for scientific_research.sci_content
     *
     * @mbggenerated
     */
    public void setSciContent(String sciContent) {
        this.sciContent = sciContent == null ? null : sciContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scientific_research.sci_project
     *
     * @return the value of scientific_research.sci_project
     *
     * @mbggenerated
     */
    public String getSciProject() {
        return sciProject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scientific_research.sci_project
     *
     * @param sciProject the value for scientific_research.sci_project
     *
     * @mbggenerated
     */
    public void setSciProject(String sciProject) {
        this.sciProject = sciProject == null ? null : sciProject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scientific_research.sci_grade
     *
     * @return the value of scientific_research.sci_grade
     *
     * @mbggenerated
     */
    public String getSciGrade() {
        return sciGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scientific_research.sci_grade
     *
     * @param sciGrade the value for scientific_research.sci_grade
     *
     * @mbggenerated
     */
    public void setSciGrade(String sciGrade) {
        this.sciGrade = sciGrade == null ? null : sciGrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scientific_research.sci_score
     *
     * @return the value of scientific_research.sci_score
     *
     * @mbggenerated
     */
    public Integer getSciScore() {
        return sciScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scientific_research.sci_score
     *
     * @param sciScore the value for scientific_research.sci_score
     *
     * @mbggenerated
     */
    public void setSciScore(Integer sciScore) {
        this.sciScore = sciScore;
    }
}