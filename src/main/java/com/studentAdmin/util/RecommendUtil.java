package com.studentAdmin.util;

import com.studentAdmin.dao.mapper.ArticleCollectMapper;
import com.studentAdmin.dao.mapper.ArticleScoreMapper;
import com.studentAdmin.domain.ArticleCollect;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.Dto.AnalyseDto;
import com.studentAdmin.domain.VOs.ArticleCollectVO;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.service.ArticleCollectService;
import com.studentAdmin.service.ArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 推荐模块
 *
 * @author: liu.yucheng
 * @Date: 2019-5-20  15:17
 * @version: 1.0
 */
@Component
public class RecommendUtil {

    @Autowired
    ArticleScoreService articleScoreService;

    @Autowired
    ArticleCollectMapper articleCollectMapper;

    @Autowired
    ArticleScoreMapper articleScoreMapper;

    /**
     * 皮尔逊推荐系数计算
     *
     * @param pm1
     * @param pm2
     * @return
     */
    public double getUserSimilar(Map<Long, Integer> pm1, Map<Long, Integer> pm2) {
        int n = 0;// 数量n
        int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
        int sx = 0;// Σx=x1+x2+....xn
        int sy = 0;// Σy=y1+y2+...yn
        int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
        int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
        for (Map.Entry<Long, Integer> pme : pm1.entrySet()) {
            Long key = pme.getKey();
            Integer x = pme.getValue();
            Integer y = pm2.get(key);
            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }
        // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
        double sd = sxy - sx * sy / (double) n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        return Math.abs(sm == 0 ? 1 : sd / sm);
    }

    /**
     * 根据用户id查询记录生成用户相关信息
     *
     * @param userId
     * @return
     */
    public List<AnalyseDto> getUserAnalyseDto(long userId) {
        //分析记录
        List<AnalyseDto> analyseDtos = new ArrayList<>();
        //获取用户评价记录
        List<ArticleScoreVO> list = articleScoreService.getArticleScoreByUserId(userId);
        for (ArticleScoreVO articleScoreVO : list) {
            analyseDtos.add(new AnalyseDto(articleScoreVO.getArticleId(), articleScoreVO.getScore(), 1));
        }
        /**
         * 计算原则 收藏 +5分  评分 + score 值   收藏+评分 score + 2
         */
        List<ArticleCollectVO> userCollects = articleCollectMapper.getUserCollect(userId);
        for (AnalyseDto analyseDto : analyseDtos) {
            for (ArticleCollectVO articleCollectVO : userCollects) {
                if (analyseDto.getArticleId() == articleCollectVO.getArticleId()) {
                    analyseDto.setScore(analyseDto.getScore() + 2);
                    analyseDto.setType(3);
                }
            }
        }
        for (ArticleCollectVO articleCollectVO : userCollects) {
            boolean exist = false;
            for (AnalyseDto analyseDto : analyseDtos) {
                if (analyseDto.getArticleId() == articleCollectVO.getArticleId()) {
                    exist = true;
                }
            }
            if (!exist) {
                analyseDtos.add(new AnalyseDto(articleCollectVO.getArticleId(), 5, 2));
            }
        }
        return analyseDtos;
    }

    /**
     * 查找分析其他用户得到相关
     *
     * @param analyseDtos
     */
    public Map<Long, List<AnalyseDto>> getOtherAnalyseDtos(List<AnalyseDto> analyseDtos) {


        Map<Long, List<AnalyseDto>> collectMap = new HashMap<>();
        Map<Long, List<AnalyseDto>> scoreMap = new HashMap<>();
        List<Long> collectIds = new ArrayList<>();
        List<Long> scoreIds = new ArrayList<>();
        for (AnalyseDto analyseDto : analyseDtos) {
            if (analyseDto.getType() == 2) {
                collectIds.add(analyseDto.getArticleId());
            }
            if (analyseDto.getType() == 1) {
                scoreIds.add(analyseDto.getArticleId());
            }
        }
        List<ArticleCollect> articleCollects = articleCollectMapper.getByArticleIds(collectIds);
        for (ArticleCollect articleCollect : articleCollects) {
            if (!collectMap.containsKey(articleCollect.getUserId())) {
                List<AnalyseDto> userCollectIds = new ArrayList<>();

                userCollectIds.add(new AnalyseDto(articleCollect.getArticleId(), 5, 2));
                collectMap.put(articleCollect.getUserId(), userCollectIds);
            } else {
                collectMap.get(articleCollect.getUserId()).add(new AnalyseDto(articleCollect.getArticleId(), 5, 2));
            }
        }
        //同理获得ScoreMap
        List<ArticleScore> articleScores = articleScoreMapper.getByArticleIds(collectIds);
        for (ArticleScore articleScore : articleScores) {
            if (!scoreMap.containsKey(articleScore.getUserId())) {
                List<AnalyseDto> userScoreIds = new ArrayList<>();
                userScoreIds.add(new AnalyseDto(articleScore.getArticleId(), articleScore.getScore(), 1));
                scoreMap.put(articleScore.getUserId(), userScoreIds);
            } else {
                scoreMap.get(articleScore.getUserId()).add(new AnalyseDto(articleScore.getArticleId(), articleScore.getScore(), 1));
            }
        }
        //整合两个Map,处理为统一格式
        Iterator<Map.Entry<Long, List<AnalyseDto>>> it = collectMap.entrySet().iterator();
        //避免it.next()无迭代造成死循环
        while (it.hasNext()&&scoreMap.keySet().size()!=0) {
            boolean exist = false;
            Long key = null;
            List<AnalyseDto> needAdd = null;
            Map.Entry<Long, List<AnalyseDto>> now = it.next();
            for (Long userId : scoreMap.keySet()) {
                if (now.getKey().equals(userId)) {
                    exist = true;
                    key = now.getKey();
                    needAdd = now.getValue();
                    it.remove();
                    break;
                }
            }
            if (exist) {
                List<AnalyseDto> analyseDtoList = this.integration(needAdd, scoreMap.get(key));
                scoreMap.put(key, analyseDtoList);
            }
        }
        scoreMap.putAll(collectMap);
        return scoreMap;
    }

    /**
     * 整合评分
     *
     * @param a1
     * @param a2
     * @return
     */
    public List<AnalyseDto> integration(List<AnalyseDto> a1, List<AnalyseDto> a2) {
        List<AnalyseDto> newAdd = new ArrayList<>();
        for (AnalyseDto v1 : a1) {
            boolean exist = false;
            AnalyseDto find = null;
            for (AnalyseDto v2 : a2) {
                if (v1.getArticleId() == v2.getArticleId()) {
                    exist = true;
                    find = v2;
                }
            }
            if (exist) {
                find.setType(3);
                find.setScore(find.getScore() + 2);
            } //防止重新比较产生的新值
            else {
                newAdd.add(v1);
            }
        }
        a2.addAll(newAdd);
        return a2;
    }

    /**
     * 系统推荐
     *
     * @param userId
     */
    public List<Long> doSystemRecommend(Long userId) {
        List<Long> recommendArticleIds = new ArrayList<>();
        List<AnalyseDto> analyseDtoList = getUserAnalyseDto(userId);
        Map<Long, Integer> loginPM = new HashMap<>();
        //获取其他用户评分
        Map<Long, Double> personMap = new HashMap<>();
        Map<Long, List<AnalyseDto>> otherAnalyseMap = getOtherAnalyseDtos(analyseDtoList);
        //删除登录用户避免识别为自己
        for (Long id : otherAnalyseMap.keySet()) {
            if (id.equals(userId)) {
                otherAnalyseMap.remove(userId);
                break;
            }
        }
        for (AnalyseDto a : analyseDtoList) {
            loginPM.put(a.getArticleId(), a.getScore());
        }
        //计算相似度存入personMap
        for (Long key : otherAnalyseMap.keySet()) {
            Map<Long, Integer> userPM = new HashMap<>();
            List<AnalyseDto> userAnalyseDtos = otherAnalyseMap.get(key);
            for (AnalyseDto a : userAnalyseDtos) {
                userPM.put(a.getArticleId(), a.getScore());
            }
            double person = getUserSimilar(loginPM, userPM);
            personMap.put(key, person);
        }
        //按照value进行降序排序
        Comparator<Map.Entry<Long, Double>> descValue = new Comparator<Map.Entry<Long, Double>>() {
            @Override
            public int compare(Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2) {
                double d1 = o1.getValue().doubleValue();
                double d2 = o2.getValue().doubleValue();
                if (d1 != d2)
                    return d1 > d2 ? -1 : 1;
                else return 0;
            }
        };
        List<Map.Entry<Long,Double>> list = new ArrayList<Map.Entry<Long,Double>>(personMap.entrySet());
        Collections.sort(list,descValue);
        personMap.clear();
        for(Map.Entry<Long,Double> map :list){
             personMap.put(map.getKey(),map.getValue());
        }
        if (personMap.keySet().size()>0) {
          a:  for (Long key : personMap.keySet()) {
                List<Long> articleIds = articleScoreMapper.getClickLog(key);
                for (Long id : articleIds) {
                    if (!recommendArticleIds.contains(id) && recommendArticleIds.size() <= 10) {
                        recommendArticleIds.add(id);
                    }
                    if(recommendArticleIds.size() == 10){
                        break a;
                    }
                }
            }
        }
        return recommendArticleIds;
    }

}
