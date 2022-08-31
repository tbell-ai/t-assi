package kr.co.tbell.tassi.service;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.ContractRequestDto;
import kr.co.tbell.tassi.dto.response.ContractResponseDto;
import kr.co.tbell.tassi.entity.Company;
import kr.co.tbell.tassi.entity.Contract;
import kr.co.tbell.tassi.entity.Users;
import kr.co.tbell.tassi.repository.CompanyRepository;
import kr.co.tbell.tassi.repository.ContractRepository;
import kr.co.tbell.tassi.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContractService {

    private final CompanyRepository companyRepository;
    private final ContractRepository contractRepository;
    private final UsersRepository usersRepository;
    private final Response response;

    public ResponseEntity<?> createContract(ContractRequestDto.Create data) {

        Company company = companyRepository.getById(data.getCompanyId());
        Users user = usersRepository.getById(data.getUserId());

        Contract contract = Contract.builder()
                .contractName(data.getContractName())
                .contractCharge(data.getContractCharge())
                .contractDay(data.getContractDay())
                .contractStart(data.getContractStart())
                .contractEnd(data.getContractEnd())
                .contractContent(data.getContractContent())
                .contractLowLevel(data.getLowLevel())
                .contractMiddleLevel(data.getMiddleLevel())
                .contractHighLevel(data.getHighLevel())
                .contractSpecialLevel(data.getSpecialLevel())
                .contractResultDelivery(data.getResultDelivery())
                .contractResultDocument(data.getResultDocument())
                .contractState("계약대기")
                .company(company)
                .user(user)
                .build();

        contractRepository.save(contract);

        return response.success("계약 생성에 성공했습니다.");
    }

    public ResponseEntity<?> getContractList(
            String contractName,
            String contractState,
            int pageNum
    ) {

        Page<Contract> contracts = contractRepository.findAllSearch(contractName, contractState, PageRequest.of(pageNum, 10, Sort.by("create_date").descending()));
        List<ContractResponseDto.ContractListData> contractList = new ArrayList<>();

        for(Contract contract : contracts) {
            ContractResponseDto.ContractListData dto = ContractResponseDto.ContractListData.builder()
                    .contactId(contract.getIdx())
                    .userId(contract.getUser().getIdx())
                    .contractName(contract.getContractName())
                    .contractDay(contract.getContractDay())
                    .contractCharge(contract.getContractCharge())
                    .companyName(contract.getCompany().getCompanyName())
                    .companyManagerName(contract.getCompany().getCompanyManagerName())
                    .build();

            contractList.add(dto);
        }

        ContractResponseDto.Paging page = ContractResponseDto.Paging.builder()
                .pageNumber(contracts.getNumber())
                .pageSize(contracts.getSize())
                .totalPages(contracts.getTotalPages())
                .numberOfElements(contracts.getNumberOfElements())
                .prev(contracts.hasPrevious())
                .next(contracts.hasNext())
                .last(contracts.isLast())
                .first(contracts.isFirst())
                .empty(contracts.isEmpty())
                .build();

        ContractResponseDto.Contract data = ContractResponseDto.Contract.builder().contractData(contractList).paging(page).build();


        return response.success(data);
    }

    public ResponseEntity<?> getContract(Long id) {
        Optional<Contract> item = contractRepository.findById(id);

        if(item.isPresent()) {
            Contract contract = item.get();

            ContractResponseDto.ContractData dto = ContractResponseDto.ContractData.builder()
                    .contractId(contract.getIdx())
                    .companyId(contract.getCompany().getIdx())
                    .userId(contract.getUser().getIdx())
                    .contractName(contract.getContractName())
                    .contractCharge(contract.getContractCharge())
                    .contractDay(contract.getContractDay())
                    .contractStart(contract.getContractStart())
                    .contractEnd(contract.getContractEnd())
                    .contractContent(contract.getContractContent())
                    .lowLevel(contract.getContractLowLevel())
                    .middleLevel(contract.getContractMiddleLevel())
                    .highLevel(contract.getContractHighLevel())
                    .specialLevel(contract.getContractSpecialLevel())
                    .resultDelivery(contract.getContractResultDelivery())
                    .resultDocument(contract.getContractResultDocument())
                    .contractState(contract.getContractState())
                    .companyManagerName(contract.getCompany().getCompanyManagerName())
                    .companyManagerPhone(contract.getCompany().getCompanyManagerPhone())
                    .createDate(contract.getCreateDate())
                    .modifiedDate(contract.getModifiedDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> updateContract(ContractRequestDto.Update data) {

        Company company = companyRepository.getById(data.getCompanyId());
        Users user = usersRepository.getById(data.getUserId());

        Contract contract = Contract.builder()
                .idx(data.getId())
                .contractName(data.getContractName())
                .contractCharge(data.getContractCharge())
                .contractDay(data.getContractDay())
                .contractStart(data.getContractStart())
                .contractEnd(data.getContractEnd())
                .contractContent(data.getContractContent())
                .contractLowLevel(data.getLowLevel())
                .contractMiddleLevel(data.getMiddleLevel())
                .contractHighLevel(data.getHighLevel())
                .contractSpecialLevel(data.getSpecialLevel())
                .contractResultDelivery(data.getResultDelivery())
                .contractResultDocument(data.getResultDocument())
                .contractState(data.getContractState())
                .company(company)
                .user(user)
                .build();

        contractRepository.save(contract);

        return response.success("계약 수정에 성공하였습니다.");
    }

    public ResponseEntity<?> deleteContract(Long id) {
        contractRepository.deleteById(id);

        return response.success("계약 삭제에 성공하였습니다.");
    }
}
