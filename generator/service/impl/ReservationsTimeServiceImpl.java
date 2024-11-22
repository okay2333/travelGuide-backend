package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ReservationsTime;
import generator.service.ReservationsTimeService;
import generator.mapper.ReservationsTimeMapper;
import org.springframework.stereotype.Service;

/**
* @author okay
* @description 针对表【reservations_time(预约时间表)】的数据库操作Service实现
* @createDate 2024-10-10 09:37:42
*/
@Service
public class ReservationsTimeServiceImpl extends ServiceImpl<ReservationsTimeMapper, ReservationsTime>
    implements ReservationsTimeService{

}




