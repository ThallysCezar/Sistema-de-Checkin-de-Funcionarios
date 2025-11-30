package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsNotFoundException;
import br.com.thallysprojetos.backenddesafio1.factories.WorkRecordFactory;
import br.com.thallysprojetos.backenddesafio1.mappers.WorkRecordMappers;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import br.com.thallysprojetos.backenddesafio1.repositories.WorkRecordRepository;
import br.com.thallysprojetos.backenddesafio1.services.impl.WorkRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("WorkRecordService - Unit Tests")
class WorkRecordServiceTest {

    @Mock
    private WorkRecordRepository workRecordRepository;

    @Mock
    private WorkRecordMappers mappers;

    @InjectMocks
    private WorkRecordServiceImpl workRecordService;

    private WorkRecord workRecord;
    private WorkRecordDTO workRecordDTO;
    private Long employeeId;

    @BeforeEach
    void setUp() {
        employeeId = 1L;
        workRecord = WorkRecordFactory.createWorkRecord();
        workRecordDTO = WorkRecordFactory.createWorkRecordDTO();
    }

    @Test
    @DisplayName("Should successfully create a check-in record when no active check-in exists")
    void testCheckIn_Success() {
        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(null);
        when(workRecordRepository.save(any(WorkRecord.class))).thenReturn(workRecord);
        when(mappers.toDTO(any(WorkRecord.class))).thenReturn(workRecordDTO);

        WorkRecordDTO result = workRecordService.checkIn(employeeId);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(employeeId);
        verify(workRecordRepository, times(1)).findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        verify(workRecordRepository, times(1)).save(any(WorkRecord.class));
        verify(mappers, times(1)).toDTO(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should throw WorkRecordsAlreadyExistException when employee already has active check-in")
    void testCheckIn_ThrowsException_WhenAlreadyCheckedIn() {
        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);

        assertThatThrownBy(() -> workRecordService.checkIn(employeeId))
                .isInstanceOf(WorkRecordsAlreadyExistException.class)
                .hasMessageContaining("already checked-in");

        verify(workRecordRepository, times(1)).findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        verify(workRecordRepository, never()).save(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should successfully perform check-out and calculate duration correctly")
    void testCheckOut_Success_WithCorrectDurationCalculation() {
        LocalDateTime checkInTime = LocalDateTime.of(2025, 11, 30, 8, 0, 0);

        workRecord.setCheckInTime(checkInTime);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);
        when(workRecordRepository.save(any(WorkRecord.class))).thenReturn(workRecord);
        when(mappers.toDTO(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord savedRecord = invocation.getArgument(0);
            WorkRecordDTO dto = new WorkRecordDTO();
            dto.setId(savedRecord.getId());
            dto.setEmployeeId(savedRecord.getEmployeeId());
            dto.setCheckInTime(savedRecord.getCheckInTime());
            dto.setCheckOutTime(savedRecord.getCheckOutTime());
            dto.setDuration(savedRecord.getDuration());
            return dto;
        });

        WorkRecordDTO result = workRecordService.checkOut(employeeId);

        assertThat(result).isNotNull();
        assertThat(result.getCheckOutTime()).isNotNull();
        assertThat(result.getDuration()).isNotNull();

        assertThat(result.getDuration()).matches("\\d{2}:\\d{2}:\\d{2}");

        verify(workRecordRepository, times(1)).findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        verify(workRecordRepository, times(1)).save(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should calculate duration correctly")
    void testCheckOut_CalculatesDuration_NineHours() {
        LocalDateTime checkInTime = LocalDateTime.of(2025, 11, 30, 8, 0, 0);

        workRecord.setCheckInTime(checkInTime);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);
        when(workRecordRepository.save(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord saved = invocation.getArgument(0);
            return saved;
        });
        when(mappers.toDTO(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord savedRecord = invocation.getArgument(0);
            WorkRecordDTO dto = new WorkRecordDTO();
            dto.setDuration(savedRecord.getDuration());
            dto.setCheckInTime(savedRecord.getCheckInTime());
            dto.setCheckOutTime(savedRecord.getCheckOutTime());
            return dto;
        });

        WorkRecordDTO result = workRecordService.checkOut(employeeId);

        assertThat(result.getDuration()).isNotNull();
        assertThat(result.getDuration()).matches("\\d{2}:\\d{2}:\\d{2}");
        assertThat(result.getCheckInTime()).isEqualTo(checkInTime);
        assertThat(result.getCheckOutTime()).isNotNull();
    }

    @Test
    @DisplayName("Should calculate duration in correct format")
    void testCheckOut_CalculatesDuration_CorrectFormat() {
        LocalDateTime checkInTime = LocalDateTime.of(2025, 11, 30, 9, 15, 0);

        workRecord.setCheckInTime(checkInTime);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);
        when(workRecordRepository.save(any(WorkRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mappers.toDTO(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord savedRecord = invocation.getArgument(0);
            WorkRecordDTO dto = new WorkRecordDTO();
            dto.setDuration(savedRecord.getDuration());
            dto.setCheckInTime(savedRecord.getCheckInTime());
            dto.setCheckOutTime(savedRecord.getCheckOutTime());
            return dto;
        });

        WorkRecordDTO result = workRecordService.checkOut(employeeId);

        assertThat(result.getDuration()).isNotNull();
        assertThat(result.getDuration()).matches("\\d{2}:\\d{2}:\\d{2}");
        assertThat(result.getCheckOutTime()).isAfter(result.getCheckInTime());
    }

    @Test
    @DisplayName("Should calculate duration with proper time format")
    void testCheckOut_CalculatesDuration_ProperFormat() {
        LocalDateTime checkInTime = LocalDateTime.of(2025, 11, 30, 10, 0, 15);

        workRecord.setCheckInTime(checkInTime);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);
        when(workRecordRepository.save(any(WorkRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mappers.toDTO(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord savedRecord = invocation.getArgument(0);
            WorkRecordDTO dto = new WorkRecordDTO();
            dto.setDuration(savedRecord.getDuration());
            dto.setCheckInTime(savedRecord.getCheckInTime());
            dto.setCheckOutTime(savedRecord.getCheckOutTime());
            return dto;
        });

        WorkRecordDTO result = workRecordService.checkOut(employeeId);

        assertThat(result.getDuration()).isNotNull();
        assertThat(result.getDuration()).matches("\\d{2}:\\d{2}:\\d{2}");
        String[] parts = result.getDuration().split(":");
        assertThat(parts).hasSize(3);
        assertThat(Integer.parseInt(parts[0])).isGreaterThanOrEqualTo(0); // horas
        assertThat(Integer.parseInt(parts[1])).isBetween(0, 59); // minutos
        assertThat(Integer.parseInt(parts[2])).isBetween(0, 59); // segundos
    }

    @Test
    @DisplayName("Should throw WorkRecordsNotFoundException when no active check-in found for check-out")
    void testCheckOut_ThrowsException_WhenNoActiveCheckIn() {
        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(null);

        assertThatThrownBy(() -> workRecordService.checkOut(employeeId))
                .isInstanceOf(WorkRecordsNotFoundException.class)
                .hasMessageContaining("No open check-in found");

        verify(workRecordRepository, times(1)).findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        verify(workRecordRepository, never()).save(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should prevent duplicate check-in for same employee")
    void testCheckIn_PreventsDuplicate() {
        WorkRecord existingCheckIn = new WorkRecord();
        existingCheckIn.setId(1L);
        existingCheckIn.setEmployeeId(employeeId);
        existingCheckIn.setCheckInTime(LocalDateTime.now().minusHours(2));
        existingCheckIn.setCheckOutTime(null);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId))
                .thenReturn(existingCheckIn);

        assertThatThrownBy(() -> workRecordService.checkIn(employeeId))
                .isInstanceOf(WorkRecordsAlreadyExistException.class)
                .hasMessageContaining("Employee " + employeeId + " already checked-in and did not check-out yet.");

        verify(workRecordRepository, never()).save(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should allow check-in after previous check-out")
    void testCheckIn_AllowsAfterPreviousCheckOut() {
        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(null);
        when(workRecordRepository.save(any(WorkRecord.class))).thenReturn(workRecord);
        when(mappers.toDTO(any(WorkRecord.class))).thenReturn(workRecordDTO);

        WorkRecordDTO result = workRecordService.checkIn(employeeId);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(employeeId);
        verify(workRecordRepository, times(1)).save(any(WorkRecord.class));
    }

    @Test
    @DisplayName("Should format duration with leading zeros")
    void testCheckOut_FormatsWithLeadingZeros() {
        LocalDateTime checkInTime = LocalDateTime.of(2025, 11, 30, 10, 0, 0);

        workRecord.setCheckInTime(checkInTime);

        when(workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId)).thenReturn(workRecord);
        when(workRecordRepository.save(any(WorkRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mappers.toDTO(any(WorkRecord.class))).thenAnswer(invocation -> {
            WorkRecord savedRecord = invocation.getArgument(0);
            WorkRecordDTO dto = new WorkRecordDTO();
            dto.setDuration(savedRecord.getDuration());
            return dto;
        });

        WorkRecordDTO result = workRecordService.checkOut(employeeId);

        assertThat(result.getDuration()).isNotNull();
        assertThat(result.getDuration()).matches("\\d{2}:\\d{2}:\\d{2}");
    }

}