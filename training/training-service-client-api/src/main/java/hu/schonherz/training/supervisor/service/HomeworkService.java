package hu.schonherz.training.supervisor.service;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.supervisor.vo.HomeworkVo;

/**
 * Service for managing homeworks.
 * 
 * @author Mark Bohan
 *
 */
public interface HomeworkService {

	/**
	 * Creates a homework.
	 * 
	 * @param homeworkVo
	 *            the homework to create
	 * @return the created homework
	 */
	public HomeworkVo createHomework(HomeworkVo homeworkVo);

	/**
	 * Deletes a homework by id.
	 * 
	 * @param id
	 *            the id of homework.
	 */
	public void deleteHomework(Long id);

	/**
	 * Finds and returns the homework by id.
	 * 
	 * @param id
	 *            the id of homework
	 * @return the homework
	 */
	public HomeworkVo getHomeworkById(Long id);

	/**
	 * Finds and returns the homework by title.
	 * 
	 * @param title
	 *            the title of homework
	 * @return the homework
	 */
	public HomeworkVo getHomeworkByTitle(String title);

	/**
	 * Returns all homework
	 * 
	 * @return homeworks
	 */
	public List<HomeworkVo> getAllHomeworks();

	/**
	 * Returns the list of homeworks that has deadline between the two
	 * parameters
	 * 
	 * @param from
	 *            the first date
	 * @param to
	 *            the second date
	 * @return the list of homeworks that has deadline between the dates
	 */
	public List<HomeworkVo> getHomeworksBetween(Date from, Date to);
}
