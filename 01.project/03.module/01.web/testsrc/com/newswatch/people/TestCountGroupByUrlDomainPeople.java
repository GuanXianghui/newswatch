package com.newswatch.people;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.UrlFilter;

/**
 * 查询所有域名前缀的数量
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Administrator
 * @version 1.0, 2015年5月4日
 * @since newswatch
 *
 */
public class TestCountGroupByUrlDomainPeople {
	public static final String PEOPLE_WEBSITE = "人民网";
	
	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		String domains = "http://119.people.com.cn,"+
				"http://2008.people.com.cn,"+
				"http://2010lianghui.people.com.cn,"+
				"http://30.people.com.cn,"+
				"http://315.auto.people.com.cn,"+
				"http://3d.people.com.cn,"+
				"http://3g.people.com.cn,"+
				"http://51fayan.people.com.cn,"+
				"http://71.people.com.cn,"+
				"http://71bbs.people.com.cn,"+
				"http://71space.people.com.cn,"+
				"http://acftu.people.com.cn,"+
				"http://acwf.people.com.cn,"+
				"http://ah.people.com.cn,"+
				"http://arabic.people.com.cn,"+
				"http://art.people.com.cn,"+
				"http://art3d.people.com.cn,"+
				"http://ask.people.com.cn,"+
				"http://australia.people.com.cn,"+
				"http://auto.people.com.cn,"+
				"http://av.people.com.cn,"+
				"http://bbs.chanjing.people.com.cn,"+
				"http://bbs.henan.people.com.cn,"+
				"http://bbs.house.people.com.cn,"+
				"http://bbs.people.com.cn,"+
				"http://bbs.sports.people.com.cn,"+
				"http://beijing.bitauto.com,"+
				"http://bf.people.com.cn,"+
				"http://bj.people.com.cn,"+
				"http://bkdy.people.com.cn,"+
				"http://blog.j.people.com.cn,"+
				"http://book.people.com.cn,"+
				"http://bookmark.people.com.cn,"+
				"http://bt.people.com.cn,"+
				"http://bz.people.com.cn,"+
				"http://caipiao.people.com.cn,"+
				"http://card.people.com.cn,"+
				"http://ccdv.people.com.cn,"+
				"http://ccn.people.com.cn,"+
				"http://ccnews.people.com.cn,"+
				"http://chinatibet.people.com.cn,"+
				"http://chinatravel.people.com.cn,"+
				"http://chinese.people.com.cn,"+
				"http://cjkeizai.j.people.com.cn,"+
				"http://comic.people.com.cn,"+
				"http://comments.people.com.cn,"+
				"http://cpc.people.com.cn,"+
				"http://cppcc.people.com.cn,"+
				"http://cq.people.com.cn,"+
				"http://culture.people.com.cn,"+
				"http://cxzy.people.com.cn,"+
				"http://dangjian.people.com.cn,"+
				"http://dangshi.people.com.cn,"+
				"http://data.fund.people.com.cn,"+
				"http://data.people.com.cn,"+
				"http://data.sports.people.com.cn,"+
				"http://daxue.edu.people.com.cn,"+
				"http://debate.people.com.cn,"+
				"http://dfgwy.edu.people.com.cn,"+
				"http://dfpd.comment.people.com.cn,"+
				"http://dg.people.com.cn,"+
				"http://diaocha.people.com.cn,"+
				"http://dl.people.com.cn,"+
				"http://download.people.com.cn,"+
				"http://dream.people.com.cn,"+
				"http://dujia.people.com.cn,"+
				"http://dv.people.com.cn,"+
				"http://edu.people.com.cn,"+
				"http://elianghui.people.com.cn,"+
				"http://energy.people.com.cn,"+
				"http://english.cpc.people.com.cn,"+
				"http://english.people.com.cn,"+
				"http://ent.people.com.cn,"+
				"http://env.people.com.cn,"+
				"http://expo.data.people.com.cn,"+
				"http://expo.people.com.cn,"+
				"http://expo2014.people.com.cn,"+
				"http://ezheng.people.com.cn,"+
				"http://fahao.game.people.com.cn,"+
				"http://fanfu.people.com.cn,"+
				"http://fangtan.people.com.cn,"+
				"http://fashion.people.com.cn,"+
				"http://finance.people.com.cn,"+
				"http://finance1.people.com.cn,"+
				"http://fj.people.com.cn,"+
				"http://french.people.com.cn,"+
				"http://fujian.people.com.cn,"+
				"http://fund.people.com.cn,"+
				"http://game.people.com.cn,"+
				"http://gbzl.people.com.cn,"+
				"http://gd.people.com.cn,"+
				"http://german.people.com.cn,"+
				"http://gjlt.people.com.cn,"+
				"http://golf.people.com.cn,"+
				"http://gonggao.people.com.cn,"+
				"http://gongyi.people.com.cn,"+
				"http://gov.people.com.cn,"+
				"http://gp.people.com.cn,"+
				"http://green.people.com.cn,"+
				"http://greenmedical.people.com.cn,"+
				"http://gs.people.com.cn,"+
				"http://gx.people.com.cn,"+
				"http://gz.people.com.cn,"+
				"http://h5.people.com.cn,"+
				"http://ha.people.com.cn,"+
				"http://hb.people.com.cn,"+
				"http://he.people.com.cn,"+
				"http://health.people.com.cn,"+
				"http://health315.people.com.cn,"+
				"http://henan.people.com.cn,"+
				"http://hgsj.people.com.cn,"+
				"http://hi.people.com.cn,"+
				"http://history.people.com.cn,"+
				"http://hlj.people.com.cn,"+
				"http://hm.people.com.cn,"+
				"http://hn.people.com.cn,"+
				"http://homea.people.com.cn,"+
				"http://hongmu.people.com.cn,"+
				"http://house.people.com.cn,"+
				"http://hsj.people.com.cn,"+
				"http://finance.people.com.cn,"+
				"http://ids.people.com.cn,"+
				"http://invest.people.com.cn,"+
				"http://investinusa.people.com.cn,"+
				"http://ip.people.com.cn,"+
				"http://it.people.com.cn,"+
				"http://itts.people.com.cn,"+
				"http://j.people.com.cn,"+
				"http://japan.people.com.cn,"+
				"http://jiaju.people.com.cn,"+
				"http://jl.people.com.cn,"+
				"http://jpn_cpc.people.com.cn,"+
				"http://js.people.com.cn,"+
				"http://jsbbs.people.com.cn,"+
				"http://jx.people.com.cn,"+
				"http://kaoshi.edu.people.com.cn,"+
				"http://kazakh.people.com.cn,"+
				"http://kf.people.com.cn,"+
				"http://kfq.people.com.cn,"+
				"http://korea.cpc.people.com.cn,"+
				"http://korea.people.com.cn,"+
				"http://korean.people.com.cn,"+
				"http://kr.people.com.cn,"+
				"http://kxfz.people.com.cn,"+
				"http://labs.people.com.cn,"+
				"http://lady.people.com.cn,"+
				"http://law.people.com.cn,"+
				"http://lcms.peopledaily.cn,"+
				"http://ldzl.people.com.cn,"+
				"http://leader.finance.people.com.cn,"+
				"http://leaders.people.com.cn,"+
				"http://legal.people.com.cn,"+
				"http://lianghui.people.com.cn,"+
				"http://liaoba.people.com.cn,"+
				"http://life.people.com.cn,"+
				"http://liuxue.people.com.cn,"+
				"http://liuxueku.people.com.cn,"+
				"http://liuyan.kazakh.people.com.cn,"+
				"http://liuyan.korean.people.com.cn,"+
				"http://liuyan.people.com.cn,"+
				"http://liuyan.uyghur.people.com.cn,"+
				"http://live.people.com.cn,"+
				"http://live.sports.people.com.cn,"+
				"http://ln.people.com.cn,"+
				"http://lottery.people.com.cn,"+
				"http://lps.people.com.cn,"+
				"http://mag.people.com.cn,"+
				"http://maillist.people.com.cn,"+
				"http://mcq.people.com.cn,"+
				"http://media.people.com.cn,"+
				"http://medicine.people.com.cn,"+
				"http://mf.people.com.cn,"+
				"http://military.people.com.cn,"+
				"http://mms.people.com.cn,"+
				"http://mnc.people.com.cn,"+
				"http://mobile.people.com.cn,"+
				"http://mongol..people.com.cn,"+
				"http://mongol.cpc.people.com.cn,"+
				"http://mongol.people.com.cn,"+
				"http://movie.people.com.cn,"+
				"http://msn.people.com.cn,"+
				"http://mydesign.people.com.cn,"+
				"http://mzxjnt.people.com.cn,"+
				"http://nb.people.com.cn,"+
				"http://news.people.com.cn,"+
				"http://newsinfo.people.com.cn,"+
				"http://nm.people.com.cn,"+
				"http://npc.people.com.cn,"+
				"http://nx.people.com.cn,"+
				"http://okooo.people.com.cn,"+
				"http://olympic.people.com.cn,"+
				"http://opinion.people.com.cn,"+
				"http://ordos.people.com.cn,"+
				"http://paike.people.com.cn,"+
				"http://paper.people.com.cn,"+
				"http://passport.people.com.cn,"+
				"http://past.people.com.cn,"+
				"http://people.com.cn,"+
				"http://pic.people.com.cn,"+
				"http://picchina.people.com.cn,"+
				"http://pmm.people.com.cn,"+
				"http://politics.people.com.cn,"+
				"http://portuguese.people.com.cn,"+
				"http://posts.people.com.cn,"+
				"http://pro.people.com.cn,"+
				"http://product.people.com.cn,"+
				"http://qd.people.com.cn,"+
				"http://qh.people.com.cn,"+
				"http://qipai.people.com.cn,"+
				"http://qxn.people.com.cn,"+
				"http://qzlx.people.com.cn,"+
				"http://rencai.people.com.cn,"+
				"http://renshi.people.com.cn,"+
				"http://renwu.people.com.cn,"+
				"http://rexian.people.com.cn,"+
				"http://riji.people.com.cn,"+
				"http://rmrbdata.people.com.cn,"+
				"http://rmyq.people.com.cn,"+
				"http://rss.people.com.cn,"+
				"http://ru.people.com.cn,"+
				"http://russian.cpc.people.com.cn,"+
				"http://russian.people.com.cn,"+
				"http://sawcuengh.people.com.cn,"+
				"http://sc.people.com.cn,"+
				"http://scitech.people.com.cn,"+
				"http://sd.people.com.cn,"+
				"http://search.people.com.cn,"+
				"http://sh.people.com.cn,"+
				"http://shipin.people.com.cn,"+
				"http://shop.people.com.cn,"+
				"http://sms.people.com.cn,"+
				"http://sn.people.com.cn,"+
				"http://sns.people.com.cn,"+
				"http://society.people.com.cn,"+
				"http://spanish.people.com.cn,"+
				"http://spfjc.people.com.cn,"+
				"http://sports.people.com.cn,"+
				"http://stu.people.com.cn,"+
				"http://su.people.com.cn,"+
				"http://sx.people.com.cn,"+
				"http://sz.people.com.cn,"+
				"http://t.people.com.cn,"+
				"http://t.people.com.cn:80,"+
				"http://tc.people.com.cn,"+
				"http://test.people.com.cn,"+
				"http://theory.people.com.cn,"+
				"http://tibet.cpc.people.com.cn,"+
				"http://tibet.people.com.cn,"+
				"http://tj.people.com.cn,"+
				"http://travel.people.com.cn,"+
				"http://travel315.people.com.cn,"+
				"http://tv.people.com.cn,"+
				"http://tw.people.com.cn,"+
				"http://uk.people.com.cn,"+
				"http://unn.people.com.cn,"+
				"http://usa.people.com.cn,"+
				"http://uyghur.people.com.cn,"+
				"http://vip.people.com.cn,"+
				"http://voice.people.com.cn,"+
				"http://w.people.com.cn,"+
				"http://wap.people.com.cn,"+
				"http://weather.people.com.cn,"+
				"http://wf.people.com.cn,"+
				"http://wireless.people.com.cn,"+
				"http://world.people.com.cn,"+
				"http://worldcup2010.people.com.cn,"+
				"http://wuxi.people.com.cn,"+
				"http://www.globalpeople.com.cn,"+
				"http://www.people.com.cn,"+
				"http://wz.people.com.cn,"+
				"http://wza.people.com.cn,"+
				"http://wzl.people.com.cn,"+
				"http://xinzheng.people.com.cn,"+
				"http://xj.people.com.cn,"+
				"http://xm.people.com.cn,"+
				"http://xz.people.com.cn,"+
				"http://yi.people.com.cn,"+
				"http://yjy.people.com.cn,"+
				"http://yn.people.com.cn,"+
				"http://yq.people.com.cn,"+
				"http://yuqing.people.com.cn,"+
				"http://z.people.com.cn,"+
				"http://z.people.com.cn:80,"+
				"http://zb.people.com.cn,"+
				"http://zcxx.people.com.cn,"+
				"http://zgbx.people.com.cn,"+
				"http://zixun.people.com.cn,"+
				"http://zj.people.com.cn";
		String[] domainArray = domains.split(",");
		for(String domain : domainArray){
			int count = NewsDao.countNewsByWebsiteAndPositionAndUrl(PEOPLE_WEBSITE, UrlFilter.FILTER_TYPE_INDEX_OF,domain);
			if(count > 200){
				System.out.println("count:[" + count + "],domain:[" + domain + "]");
			}
		}
	}
	
	/**
	 * 入口方法
	 * @param params
	 * @throws Exception
	 */
	public static void main(String[] params) throws Exception{
		TestCountGroupByUrlDomainPeople queryAllUrlDomainPeople = new TestCountGroupByUrlDomainPeople();
		queryAllUrlDomainPeople.process();
	}
}
